package com.example.hrd.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hrd.myapplication.Adapter.DepartmentAdapter;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.Util.CommonUtil;
import com.example.hrd.myapplication.Util.RxthrottleFirst;
import com.example.hrd.myapplication.Util.ViewUtil;
import com.example.hrd.myapplication.bean.DepartmentBean;
import com.example.hrd.myapplication.ui.base.BaseFramgent;
import com.example.hrd.myapplication.ui.fragment.DepartmentConstracts.DepartmentConstracts;
import com.example.hrd.myapplication.ui.fragment.DepartmentConstracts.DepartmentModel;
import com.example.hrd.myapplication.ui.fragment.DepartmentConstracts.DepartmentPresenter;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class DepartmentFragment extends BaseFramgent<DepartmentPresenter,DepartmentModel> implements DepartmentConstracts.DepartmentMview,BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.my_recycler)
    public RecyclerView recyclerView;
    public DepartmentAdapter adapter;
    public static DepartmentFragment newInstance(Bundle args) {

//        Bundle args = new Bundle();

        DepartmentFragment fragment = new DepartmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {
        initView();
        initToolBar();
    }

    private void initView() {
        ViewUtil.initRecyclerViewStyle(mContext,recyclerView, 2);
        mPresenter.getList(getArguments().getString(CommonUtil.StoreData.StoreCode));
    }

    private void initToolBar() {
        toolbar.setTitle("请选择部门");
        RxToolbar.navigationClicks(toolbar).compose(RxthrottleFirst.applyThrottleFirst()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                pop();
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle args=new Bundle();
        args.putString(CommonUtil.StoreData.StoreCode,getArguments().getString(CommonUtil.StoreData.StoreCode));
        args.putString(CommonUtil.Department.DepartmentCode,((DepartmentBean)adapter.getData().get(position)).getDeptcode());
        start(DetailFragment.newInstance(args));
    }

    @Override
    public void UpdataView(List<DepartmentBean> mlist) {
        adapter=new DepartmentAdapter(mContext,R.layout.item_store,mlist);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void showError(String msg) {
        ViewUtil.showToast(msg);
    }

    @Override
    public void updateData(String msg) {

    }

}
