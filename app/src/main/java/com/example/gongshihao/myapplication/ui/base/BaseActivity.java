package com.example.gongshihao.myapplication.ui.base;

import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;

import com.example.gongshihao.myapplication.Util.TUtil;
import com.example.gongshihao.myapplication.mvp.BaseModel;
import com.example.gongshihao.myapplication.mvp.BasePresenter;
import com.example.gongshihao.myapplication.mvp.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * Created by Jacky on 2017/5/25.
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends SupportActivity {

    public Unbinder unbinder;
    public T mPresenter;
    public E mModel;
    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
//        Log.e("对象",mPresenter+"---"+mModel);
        if (this instanceof BaseView) mPresenter.attachVM(this, mModel);
//        StyledDialog.init(getApplicationContext());
        initCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
        if (mPresenter != null) mPresenter.detachVM();
    }

    protected void setToolBar(Toolbar toolbar, boolean showLeft) {
        toolbar.setTitle("");

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(showLeft);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressedSupport();
//            }
//        });
    }

    protected abstract int getLayoutId();

    protected abstract void initCreate(Bundle savedInstanceState);

    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart("main");
//        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd("main");
//        MobclickAgent.onPause(this);
    }

}
