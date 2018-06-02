package com.example.gongshihao.myapplication.ui.fragment;


import android.Manifest;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gongshihao.myapplication.Adapter.MainAdapter;
import com.example.gongshihao.myapplication.ImageUtil.GlideUtil;
import com.example.gongshihao.myapplication.R;
import com.example.gongshihao.myapplication.Util.RxthrottleFirst;
import com.example.gongshihao.myapplication.Util.ViewUtil;
import com.example.gongshihao.myapplication.Weight.DividerItemDecoration;
import com.example.gongshihao.myapplication.bean.MainMenuBean;
import com.example.gongshihao.myapplication.ui.activity.MainActivityConstracts.MainConstracts;
import com.example.gongshihao.myapplication.ui.activity.MainActivityConstracts.MainModel;
import com.example.gongshihao.myapplication.ui.activity.MainActivityConstracts.MainPresenter;
import com.example.gongshihao.myapplication.ui.base.BaseFramgent;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.support.v7.widget.SearchViewQueryTextEvent;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.pgyersdk.update.PgyUpdateManager;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;


/**
 * Created by gongshihao on 2018/4/14.
 */

public class MainFragment extends BaseFramgent<MainPresenter,MainModel> implements MainConstracts.MainMview{
    @BindView(R.id.my_recycler)
    public RecyclerView MyRecyclerView;
    @BindView(R.id.myRollPagerView)
    public RollPagerView rollPagerView;
    @BindView(R.id.mDrawerLayout)
    public DrawerLayout drawerLayout;
    public final int REQUEST_CODE=0x01;
    public SearchView mSearchView;
    public SearchView.SearchAutoComplete mSearchAutoComplete;
    RxPermissions rxPermissions;
    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {
        initView();
        CheckUpdate();
    }

    void toggle() {
        int drawerLockMode = drawerLayout.getDrawerLockMode(GravityCompat.START);
        if (drawerLayout.isDrawerVisible(GravityCompat.START)
                && (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void CheckUpdate() {
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean){
                    rxPermissions.requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE)
                            .subscribe(new Consumer<Permission>() {
                                @Override
                                public void accept(Permission permission) throws Exception {
                                    if (permission.granted){
                                        PgyUpdateManager.setIsForced(true);
                                        PgyUpdateManager.register(mActivity);
                                    }else if(permission.shouldShowRequestPermissionRationale){
                                        ViewUtil.showToast("自动更新失败,授权错误");
                                    }else{
                                        ViewUtil.showToast("授权错误,请到设置页面手动打开权限");
                                    }
                                }
                            });
                }else{
                    PgyUpdateManager.setIsForced(true);
                    PgyUpdateManager.register(mActivity);
                }

            }
        });
    }


    private void initView() {
          rxPermissions=new RxPermissions(mActivity);
          toolbar.setNavigationIcon(R.mipmap.xiaoren);
          ViewUtil.initRecyclerViewStyle(mContext,MyRecyclerView,2);
          RxToolbar.navigationClicks(toolbar).compose(RxthrottleFirst.applyThrottleFirst()).subscribe(new Consumer<Object>() {
              @Override
              public void accept(Object o) throws Exception {
                  toggle();
              }
          });
          RxToolbar.itemClicks(toolbar)
                  .compose(RxthrottleFirst.<MenuItem>applyThrottleFirst())
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA))
                  .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {

                    if (aBoolean){
                        Intent intent=new Intent(mContext,CaptureActivity.class);
                        mActivity.startActivityForResult(intent,REQUEST_CODE);
                    }else{
                        rxPermissions.requestEachCombined(Manifest.permission.CAMERA).subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
                                if (permission.granted){
                                    Intent intent=new Intent(mContext,CaptureActivity.class);
                                    mActivity.startActivityForResult(intent,REQUEST_CODE);
                                }else if(permission.shouldShowRequestPermissionRationale){
                                    Toast.makeText(mContext,"应用使用期间需要该权限,请允许",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(mContext,"已拒绝该权限,请手动设置",Toast.LENGTH_LONG).show();
                                }
                        }
                    });
                }
            }
        });
          rollPagerView.setAdapter(new LoopPagerAdapter(rollPagerView) {
              private int[] imgs = {
                      R.mipmap.show1,R.mipmap.show2
              };
              @Override
              public View getView(ViewGroup container, int position) {
                  ImageView view = new ImageView(container.getContext());
                  view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                  view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                  GlideUtil.LoadCommonImage(mContext,imgs[position],view);
                  return view;
              }

              @Override
              public int getRealCount() {
                  return imgs.length;
              }
          });
          mPresenter.getMainMenu();

    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu,menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setIconified(true);
        mSearchView.setSubmitButtonEnabled(true);
//        mSearchAutoComplete=mSearchView.findViewById(R.id.search_src_text);
//        mSearchAutoComplete.setTextColor(getResources().getColor(R.color.colorGray1));
        RxSearchView.queryTextChangeEvents(mSearchView).throttleFirst(1,TimeUnit.MICROSECONDS).subscribe(new Consumer<SearchViewQueryTextEvent>() {
            @Override
            public void accept(SearchViewQueryTextEvent searchViewQueryTextEvent) throws Exception {
                if(searchViewQueryTextEvent.isSubmitted()){
                    Toast.makeText(mContext,searchViewQueryTextEvent.queryText(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE:
                    if(data!=null){
                        Bundle bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                            String result = bundle.getString(CodeUtils.RESULT_STRING);
                            Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void EquipAdapter(List<MainMenuBean> mList) {
        MyRecyclerView.setAdapter(new MainAdapter(mContext,R.layout.layout_item_home,mList));
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void updateData(String msg) {

    }
}
