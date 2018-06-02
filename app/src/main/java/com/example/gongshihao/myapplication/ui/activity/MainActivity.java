package com.example.gongshihao.myapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gongshihao.myapplication.R;
import com.example.gongshihao.myapplication.ui.base.BaseActivity;
import com.example.gongshihao.myapplication.ui.fragment.MainFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mainpage;
    }

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.FrameLayout, MainFragment.newInstance());
        }
    }

}
