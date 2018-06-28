package com.example.hrd.myapplication.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hrd.myapplication.MyApplication;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.Util.CommonUtil;
import com.example.hrd.myapplication.Util.ViewUtil;
import com.example.hrd.myapplication.bean.User;
import com.example.hrd.myapplication.ui.base.BaseFramgent;
import com.example.hrd.myapplication.ui.fragment.PropertyConstracts.PropertyConstracts;
import com.example.hrd.myapplication.ui.fragment.PropertyConstracts.PropertyModel;
import com.example.hrd.myapplication.ui.fragment.PropertyConstracts.PropertyPresenter;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class PropertyFragment extends BaseFramgent<PropertyPresenter,PropertyModel> implements PropertyConstracts.PropertyMview {
    @BindView(R.id.StoreName)
    public TextView StoreNameText;
    @BindView(R.id.equipName)
    public TextView EquipNameText;
    @BindView(R.id.model)
    public TextView ModelText;
    @BindView(R.id.department)
    public TextView DepartmentText;
    @BindView(R.id.value)
    public TextView ValueText;
    @BindView(R.id.EquipId)
    public TextView EquipIdText;
    @BindView(R.id.Comment)
    public EditText CommentText;
    public static PropertyFragment newInstance(Bundle args) {
        
//        Bundle args = new Bundle();
        
        PropertyFragment fragment = new PropertyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_property;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {
        InitView();
        InitToolbar();
    }

    private void InitToolbar() {
        StyledDialog.init(mContext);
        RxToolbar.navigationClicks(toolbar).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                pop();
            }
        });
    }

    private void InitView() {
        StoreNameText.setText(getArguments().getString(CommonUtil.Equip.STORENAME));
        EquipNameText.setText(getArguments().getString(CommonUtil.Equip.EQUIPNAME));
        ModelText.setText(getArguments().getString(CommonUtil.Equip.MODEL));
        DepartmentText.setText(getArguments().getString(CommonUtil.Equip.DEPARTMENT));
        ValueText.setText(getArguments().getString(CommonUtil.Equip.VALUE));
        EquipIdText.setText(getArguments().getString(CommonUtil.Equip.EQUIPID));

    }
    @OnClick({R.id.CheckBtn,R.id.ExceptionBtn})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.CheckBtn:
                StyledDialog.buildLoading("上报中...").show();
                mPresenter.PostComment(User.getUser().getUserName(),getArguments().getString(CommonUtil.Equip.EQUIPID),CommentText.getText().toString(),"1");
                break;
            case R.id.ExceptionBtn:
                StyledDialog.buildLoading("上报中...").show();
                mPresenter.PostComment(User.getUser().getUserName(),getArguments().getString(CommonUtil.Equip.EQUIPID),CommentText.getText().toString(),"2");
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void ShowDialog(String message) {
        StyledDialog.dismissLoading();
        StyledDialog.buildIosAlert(getResources().getString(R.string.DialogTitle), message, new MyDialogListener() {
            @Override
            public void onFirst() {

            }

            @Override
            public void onSecond() {

            }
        }).show();
    }

    @Override
    public void showError(String msg) {
        StyledDialog.dismissLoading();
        ViewUtil.showToast(msg);
    }

    @Override
    public void updateData(String msg) {

    }
}
