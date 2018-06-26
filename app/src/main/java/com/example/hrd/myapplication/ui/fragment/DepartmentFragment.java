package com.example.hrd.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.Util.RxthrottleFirst;
import com.example.hrd.myapplication.Util.ViewUtil;
import com.example.hrd.myapplication.ui.base.BaseFramgent;
import com.example.hrd.myapplication.ui.fragment.DepartmentConstracts.DepartmentModel;
import com.example.hrd.myapplication.ui.fragment.DepartmentConstracts.DepartmentPresenter;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class DepartmentFragment extends BaseFramgent<DepartmentPresenter,DepartmentModel> {

    @BindView(R.id.my_recycler)
    public RecyclerView recyclerView;

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
        ViewUtil.initRecyclerViewStyle(mContext,recyclerView, LinearLayout.VERTICAL);
    }

    private void initToolBar() {
        RxToolbar.navigationClicks(toolbar).compose(RxthrottleFirst.applyThrottleFirst()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                PressBack();
            }
        });
    }
}
