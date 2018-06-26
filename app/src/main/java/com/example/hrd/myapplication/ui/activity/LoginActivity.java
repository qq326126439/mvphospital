package com.example.hrd.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.hrd.myapplication.Constracts.NetworkConstracts;
import com.example.hrd.myapplication.MyApplication;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.Util.CommonUtil;
import com.example.hrd.myapplication.Util.SPUtils;
import com.example.hrd.myapplication.Util.ViewUtil;
import com.example.hrd.myapplication.bean.User;
import com.example.hrd.myapplication.ui.activity.LoginConstrracts.LoginConstracts;
import com.example.hrd.myapplication.ui.activity.LoginConstrracts.LoginModel;
import com.example.hrd.myapplication.ui.activity.LoginConstrracts.LoginPresenter;
import com.example.hrd.myapplication.ui.base.BaseActivity;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements LoginConstracts.LoginMview{
    @BindView(R.id.Edit_Ip)
    public EditText TextIp;
    @BindView(R.id.Edit_Account)
    public EditText TextAccount;
    @BindView(R.id.Edit_Password)
    public EditText TextPassword;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        StyledDialog.init(mContext);
        TextAccount.setText(SPUtils.getInstance().getString(CommonUtil.UserData.UserName,""));
        TextIp.setText(SPUtils.getInstance().getString(CommonUtil.UserData.NetWork,""));
    }

    @Override
    public void setToolbar() {
        if (toolbar!=null){
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.logo);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        }
    }

    public String CheckInputInfo(){
        return CommonUtil.CheckNotNull(TextIp.getText().toString())?CommonUtil.CheckNotNull(TextAccount.getText().toString())?"":"请输入账号":"请填写服务器地址";
    }

    @OnClick({R.id.submit_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.submit_login:
                String Toast=CheckInputInfo();
                if (Toast.equals("")){
                    StyledDialog.buildLoading("加载中...").show();
                    SPUtils.getInstance().put(CommonUtil.UserData.UserName,TextAccount.getText().toString());
                    SPUtils.getInstance().put(CommonUtil.UserData.NetWork,TextIp.getText().toString().trim());
                    User.getUser().setUrl("http://"+SPUtils.getInstance().getString(CommonUtil.UserData.NetWork)+"/"+ NetworkConstracts.WebServiceUrl);
                    Log.e("test",User.getUser().getUrl());
                    mPresenter.Login(SPUtils.getInstance().getString(CommonUtil.UserData.NetWork));

                }else {
                    StyledDialog.buildIosAlert(getResources().getString(R.string.DialogTitle), Toast, new MyDialogListener() {
                        @Override
                        public void onFirst() {

                        }

                        @Override
                        public void onSecond() {

                        }
                    }).show();
                }

                break;
        }
    }

    @Override
    public void showError(String msg) {
        StyledDialog.dismissLoading();
        StyledDialog.buildIosAlert(getResources().getString(R.string.DialogTitle),msg,new MyDialogListener(){
            @Override
            public void onFirst() {

            }

            @Override
            public void onSecond() {

            }
        }).show();
    }

    @Override
    public void updateData(String msg) {
        StyledDialog.dismissLoading();
        Intent intent=new Intent(mContext,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = ( InputMethodManager ) MyApplication.getContext().getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive()) {
            imm.hideSoftInputFromWindow(toolbar.getApplicationWindowToken( ) , 0 );
        }
        return super.onTouchEvent(event);
    }
}
