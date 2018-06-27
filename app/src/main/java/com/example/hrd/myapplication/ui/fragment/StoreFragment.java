package com.example.hrd.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hrd.myapplication.Adapter.StoreAdapter;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.Util.CommonUtil;
import com.example.hrd.myapplication.Util.RxthrottleFirst;
import com.example.hrd.myapplication.Util.ViewUtil;
import com.example.hrd.myapplication.bean.StoreBean;
import com.example.hrd.myapplication.ui.base.BaseFramgent;
import com.example.hrd.myapplication.ui.fragment.StoreConstracts.StoreConstracts;
import com.example.hrd.myapplication.ui.fragment.StoreConstracts.StoreModel;
import com.example.hrd.myapplication.ui.fragment.StoreConstracts.StorePresenter;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class StoreFragment extends BaseFramgent<StorePresenter,StoreModel> implements StoreConstracts.StoreMview,BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.my_recycler)
    public RecyclerView recyclerView;
    public StoreAdapter adapter;
    public static StoreFragment newInstance() {

        Bundle args = new Bundle();

        StoreFragment fragment = new StoreFragment();
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
        ViewUtil.initRecyclerViewStyle(mContext,recyclerView,2);
        mPresenter.getList();
    }

//    @Override
//    public void setToolbar() {
//      if(toolbar!=null){
//            toolbar.setNavigationIcon(R.mipmap.backspace);
//            toolbar.setTitle("请选择库");
//        }
//    }


    private void initToolBar() {
        RxToolbar.navigationClicks(toolbar).compose(RxthrottleFirst.applyThrottleFirst()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                onBackPressedSupport();
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle=new Bundle();
        bundle.putString(CommonUtil.StoreData.StoreCode,((StoreBean) adapter.getData().get(position)).getStorageroomcode());
        bundle.putString(CommonUtil.StoreData.StoreName,((StoreBean) adapter.getData().get(position)).getStorageroomname());
        Start(bundle);
    }

    @Override
    public void UpdateView(List<StoreBean> list) {
        adapter=new StoreAdapter(mContext,R.layout.item_store,list);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String msg) {
        ViewUtil.showToast(msg);
    }

    @Override
    public void updateData(String msg) {

    }

    public void Start(Bundle args){
//        StartBrotherEvent event=new StartBrotherEvent(DepartmentFragment.newInstance(args));
//        EventBus.getDefault().post(event);
        start(DepartmentFragment.newInstance(args));
    }
}
