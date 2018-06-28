package com.example.hrd.myapplication.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hrd.myapplication.Adapter.EquipmentAdapter;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.Util.CommonUtil;
import com.example.hrd.myapplication.Util.RxthrottleFirst;
import com.example.hrd.myapplication.Util.ViewUtil;
import com.example.hrd.myapplication.bean.EquipmentBean;
import com.example.hrd.myapplication.ui.base.BaseFramgent;
import com.example.hrd.myapplication.ui.fragment.DetailConstracts.DetailConstracts;
import com.example.hrd.myapplication.ui.fragment.DetailConstracts.DetailModel;
import com.example.hrd.myapplication.ui.fragment.DetailConstracts.DetailPresenter;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.support.v7.widget.SearchViewQueryTextEvent;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class DetailFragment extends BaseFramgent<DetailPresenter,DetailModel> implements DetailConstracts.DetailMview ,BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.my_recycler)
    public RecyclerView recyclerView;
    public SearchView mSearchView;
    public EquipmentAdapter adapter;
    public RxPermissions rxPermissions;
    public final int REQUEST_CODE=0x01;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_equipment;
    }

    public static DetailFragment newInstance(Bundle args) {
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void viewCreate(Bundle savedInstanceState) {
        initView();
        mPresenter.getList(getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),"ALL");
    }

    private void initView() {
        toolbar.setTitle("资产明细");
        rxPermissions=new RxPermissions(mActivity);
        ViewUtil.initRecyclerViewStyle(mContext,recyclerView, LinearLayout.VERTICAL);
        RxToolbar.navigationClicks(toolbar).compose(RxthrottleFirst.applyThrottleFirst()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                pop();
            }
        });
        RxToolbar.itemClicks(toolbar)
                .compose(RxthrottleFirst.<MenuItem>applyThrottleFirst())
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        if (aBoolean){
                            Intent intent=new Intent(mContext,CaptureActivity.class);
                            mActivity.startActivityForResult(intent,REQUEST_CODE);
                        }else{
                            rxPermissions.requestEachCombined(Manifest.permission.CAMERA).subscribe(new Consumer<Permission>() {
                                @Override
                                public void accept(Permission permission) throws Exception {
                                    if (permission.granted){
                                        Intent intent=new Intent(mContext,CaptureActivity.class);
                                        mActivity.startActivityForResult(intent,REQUEST_CODE);
                                    }else if(permission.shouldShowRequestPermissionRationale){
                                        Toast.makeText(mContext,"应用使用期间需要该权限,请允许",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(mContext,"已拒绝该权限,请手动设置",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });
    }


    @OnClick({R.id.never,R.id.all,R.id.already})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.never:
                if(adapter!=null)
                    adapter.setNewData(null);
                mPresenter.getList(getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),"INCOMPLETE");
                break;
            case R.id.all:
                if(adapter!=null)
                    adapter.setNewData(null);
                mPresenter.getList(getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),"ALL");
                break;
            case R.id.already:
                if(adapter!=null)
                    adapter.setNewData(null);
                mPresenter.getList(getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),"FINISH");
                break;
        }
    }

    @Override
    public void Updatea(List<EquipmentBean> mlist) {
        if(adapter==null){
            adapter=new EquipmentAdapter(mContext,R.layout.item_equitment,mlist);
            adapter.addHeaderView(getLayoutInflater().inflate(R.layout.item_equipment_header,null));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
        }else{
            adapter.setNewData(mlist);
        }
    }

    @Override
    public void showError(String msg) {
        ViewUtil.showToast(msg);
    }

    @Override
    public void updateData(String msg) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle args=new Bundle();
        args.putString(CommonUtil.Equip.DEPARTMENT,((EquipmentBean)adapter.getData().get(position)).getDeptname());
        args.putString(CommonUtil.Equip.EQUIPID,((EquipmentBean)adapter.getData().get(position)).getEquipmentnum());
        args.putString(CommonUtil.Equip.EQUIPNAME,((EquipmentBean)adapter.getData().get(position)).getEquipmentname());
        args.putString(CommonUtil.Equip.MODEL,((EquipmentBean)adapter.getData().get(position)).getModel());
        args.putString(CommonUtil.Equip.VALUE,((EquipmentBean)adapter.getData().get(position)).getValue());
        args.putString(CommonUtil.Equip.STORENAME,((EquipmentBean)adapter.getData().get(position)).getStoragename());
        start(PropertyFragment.newInstance(args));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu,menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setIconified(true);
        mSearchView.setSubmitButtonEnabled(true);
        RxSearchView.queryTextChangeEvents(mSearchView).throttleFirst(1, TimeUnit.MICROSECONDS).subscribe(new Consumer<SearchViewQueryTextEvent>() {
            @Override
            public void accept(SearchViewQueryTextEvent searchViewQueryTextEvent) throws Exception {
                if(searchViewQueryTextEvent.isSubmitted()){
                    Toast.makeText(mContext,searchViewQueryTextEvent.queryText(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter=null;
        rxPermissions=null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE:
                    if(data!=null){
                        Bundle bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                            String result = bundle.getString(CodeUtils.RESULT_STRING);
                            Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
            }
        }
    }
}
