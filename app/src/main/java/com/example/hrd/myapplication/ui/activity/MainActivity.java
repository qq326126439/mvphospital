package com.example.hrd.myapplication.ui.activity;

import android.os.Bundle;
import com.example.hrd.myapplication.EventBus.StartBrotherEvent;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.ui.base.BaseActivity;
import com.example.hrd.myapplication.ui.fragment.StoreFragment;
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
