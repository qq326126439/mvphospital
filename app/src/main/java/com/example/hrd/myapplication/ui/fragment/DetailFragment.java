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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class DetailFragment extends BaseFramgent<DetailPresenter,DetailModel> implements DetailConstracts.DetailMview ,BaseQuickAdapter.OnItemClickListener ,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.my_recycler)
    public RecyclerView recyclerView;
    public SearchView mSearchView;
    public EquipmentAdapter adapter;
    public RxPermissions rxPermissions;
    @BindView(R.id.all) public TextView btnAll;
    @BindView(R.id.already) public TextView btnAlready;
    @BindView(R.id.never) public TextView btnNever;
    public String clickType="INCOMPLETE";//当前查询的项目
    public int nowPage=0;//页数
    public String nowTitle="";
    public final int REQUEST_CODE=0x01;
    public final int REQUEST_RESULT=0x20;
    public boolean IsLoadMore=false;
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
        nowTitle=getArguments().getString(CommonUtil.Department.DEPARTMENTNAME)+"未盘点";
        toolbar.setTitle(nowTitle);
        mPresenter.getList("",getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),clickType,String.valueOf(nowPage));
    }

    private void initView() {
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
                            startActivityForResult(intent,REQUEST_CODE);
                        }else{
                            rxPermissions.requestEachCombined(Manifest.permission.CAMERA).subscribe(new Consumer<Permission>() {
                                @Override
                                public void accept(Permission permission) throws Exception {
                                    if (permission.granted){
                                        Intent intent=new Intent(mContext,CaptureActivity.class);
                                        startActivityForResult(intent,REQUEST_CODE);
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
            case R.id.never://未盘点
                IsLoadMore=false;
                if(adapter!=null){
                    adapter.setNewData(null);
                    adapter.setEnableLoadMore(true);
                }
                clickType="INCOMPLETE";
                nowPage=0;
                nowTitle=getArguments().getString(CommonUtil.Department.DEPARTMENTNAME)+"未盘点";
                toolbar.setTitle(nowTitle);


                btnNever.setEnabled(false);
                mPresenter.getList("",getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),clickType,String.valueOf(nowPage));
                btnNever.setEnabled(true);
                break;
            case R.id.all://所有
                IsLoadMore=false;
                if(adapter!=null) {
                    adapter.setEnableLoadMore(true);
                    adapter.setNewData(null);
                }
                clickType="ALL";
                nowPage=0;
                nowTitle=getArguments().getString(CommonUtil.Department.DEPARTMENTNAME)+"所有资产";
                toolbar.setTitle(nowTitle);
                btnAll.setEnabled(false);
                mPresenter.getList("",getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),clickType,String.valueOf(nowPage));
                btnAll.setEnabled(true);
                break;
            case R.id.already://已盘点
                IsLoadMore=false;
                if(adapter!=null){
                    adapter.setNewData(null);
                    adapter.setEnableLoadMore(true);
                }
                clickType="FINISH";
                nowPage=0;
                nowTitle=getArguments().getString(CommonUtil.Department.DEPARTMENTNAME)+"已盘点";
                toolbar.setTitle(nowTitle);


                btnAlready.setEnabled(false);
                mPresenter.getList("",getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),clickType,String.valueOf(nowPage));
                btnAlready.setEnabled(true);
                break;
        }
    }

    @Override
    public void Updatea(List<EquipmentBean> mlist) {
        if(adapter==null){
            adapter=new EquipmentAdapter(mContext,R.layout.item_equitment,mlist);
            adapter.addHeaderView(getLayoutInflater().inflate(R.layout.item_equipment_header,null));
            recyclerView.setAdapter(adapter);
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.setOnItemClickListener(this);
        }else{
            if(IsLoadMore){
                adapter.addData(mlist);
                adapter.loadMoreComplete();
            }else {
                adapter.setNewData(mlist);
            }
        }
    }

    @Override
    public void showError(String msg) {
        ViewUtil.showToast(msg);
        if(adapter!=null){
            adapter.setNewData(null);
            adapter.setEnableLoadMore(false);
            adapter.loadMoreEnd();
            adapter.loadMoreFail();
        }
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
        args.putString(CommonUtil.Equip.SIGNDATA,((EquipmentBean)adapter.getData().get(position)).getSigndata());
        startForResult(PropertyFragment.newInstance(args),REQUEST_RESULT);
    }

    @Override//加载下一页
    public void onLoadMoreRequested() {
        if(adapter.getData().size()>=20) {
            IsLoadMore = true;
            nowPage++;
            mPresenter.getList("",getArguments().getString(CommonUtil.StoreData.STORECODE), getArguments().getString(CommonUtil.Department.DEPARTMENTCODE), clickType, String.valueOf(nowPage));
        }else{
            adapter.setEnableLoadMore(false);
            adapter.loadMoreFail();
        }
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
                    if(searchViewQueryTextEvent.queryText().toString().trim().length()>0) {
//                    Toast.makeText(mContext,searchViewQueryTextEvent.queryText(),Toast.LENGTH_LONG).show();
                        mPresenter.getList(searchViewQueryTextEvent.queryText().toString(), getArguments().getString(CommonUtil.StoreData.STORECODE), getArguments().getString(CommonUtil.Department.DEPARTMENTCODE), "search", String.valueOf(nowPage));
                    }
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
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_RESULT:
                nowPage=0;
                mPresenter.getList("",getArguments().getString(CommonUtil.StoreData.STORECODE),getArguments().getString(CommonUtil.Department.DEPARTMENTCODE),clickType,String.valueOf(nowPage));
                break;
        }
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
//                            Toast.makeText(mContext,"jieguo"+result,Toast.LENGTH_LONG).show();
                            mPresenter.SearchById(result);
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;

            }
        }
    }

    @Override
    public void Goto(EquipmentBean bean) {
        if(bean!=null){
            Bundle args=new Bundle();
            args.putString(CommonUtil.Equip.DEPARTMENT,bean.getDeptname());
            args.putString(CommonUtil.Equip.EQUIPID, bean.getEquipmentnum());
            args.putString(CommonUtil.Equip.EQUIPNAME, bean.getEquipmentname());
            args.putString(CommonUtil.Equip.MODEL, bean.getModel());
            args.putString(CommonUtil.Equip.VALUE, bean.getValue());
            args.putString(CommonUtil.Equip.STORENAME, bean.getStoragename());
            args.putString(CommonUtil.Equip.SIGNDATA, bean.getSigndata());

            startForResult(PropertyFragment.newInstance(args),REQUEST_RESULT);

        }
    }
}
