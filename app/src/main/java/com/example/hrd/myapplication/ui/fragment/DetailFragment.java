package com.example.hrd.myapplication.ui.fragment;

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
        mPresenter.getList(getArguments().getString(CommonUtil.StoreData.StoreCode),getArguments().getString(CommonUtil.Department.DepartmentCode),"ALL");
    }

    private void initView() {
        toolbar.setTitle("资产明细");
        ViewUtil.initRecyclerViewStyle(mContext,recyclerView, LinearLayout.VERTICAL);
        RxToolbar.navigationClicks(toolbar).compose(RxthrottleFirst.applyThrottleFirst()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                pop();
            }
        });
    }


    @OnClick({R.id.never,R.id.all,R.id.already})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.never:
                if(adapter!=null)
                    adapter.setNewData(null);
                mPresenter.getList(getArguments().getString(CommonUtil.StoreData.StoreCode),getArguments().getString(CommonUtil.Department.DepartmentCode),"INCOMPLETE");
                break;
            case R.id.all:
                if(adapter!=null)
                    adapter.setNewData(null);
                mPresenter.getList(getArguments().getString(CommonUtil.StoreData.StoreCode),getArguments().getString(CommonUtil.Department.DepartmentCode),"ALL");
                break;
            case R.id.already:
                if(adapter!=null)
                    adapter.setNewData(null);
                mPresenter.getList(getArguments().getString(CommonUtil.StoreData.StoreCode),getArguments().getString(CommonUtil.Department.DepartmentCode),"FINISH");
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

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu,menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setIconified(true);
        mSearchView.setSubmitButtonEnabled(true);
//        mSearchAutoComplete=mSearchView.findViewById(R.id.search_src_text);
//        mSearchAutoComplete.setTextColor(getResources().getColor(R.color.colorGray1));
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
    }
}
