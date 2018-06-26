package com.example.hrd.myapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hrd.myapplication.EventBus.StartBrotherEvent;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.ui.base.BaseActivity;
import com.example.hrd.myapplication.ui.fragment.MainFragment;
import com.example.hrd.myapplication.ui.fragment.StoreFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mainpage;
    }

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            EventBus.getDefault().register(mContext);
            loadRootFragment(R.id.FrameLayout, StoreFragment.newInstance());
        }
    }
    @Subscribe
    public void StartFrament(StartBrotherEvent startBrotherEvent){
        start(startBrotherEvent.targetFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mContext);
    }
}
