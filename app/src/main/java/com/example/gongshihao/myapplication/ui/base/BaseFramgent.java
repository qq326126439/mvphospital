package com.example.gongshihao.myapplication.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.gongshihao.myapplication.Util.TUtil;
import com.example.gongshihao.myapplication.mvp.BaseModel;
import com.example.gongshihao.myapplication.mvp.BasePresenter;
import com.example.gongshihao.myapplication.mvp.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Jacky on 2017/5/25.
 */

public abstract class   BaseFramgent <T extends BasePresenter, E extends BaseModel> extends SupportFragment {

    protected View mRootView;
    public Unbinder unbinder;
    protected Context mContext;
    protected Activity mActivity;
    public T mPresenter;
    public E mModel;

//    private ListCompositeDisposable disposablesStop;// 管理Stop取消订阅者者
//    private ListCompositeDisposable disposablesDestroy;// 管理Destroy取消订阅者者


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (disposablesDestroy != null) {
//            throw new IllegalStateException("onCreate called multiple times");
//        }
//        disposablesDestroy = new ListCompositeDisposable();
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        Log.e("对象",mPresenter+"---"+mModel);
        if (this instanceof BaseView) mPresenter.attachVM(this, mModel);
        viewCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
        mContext = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) unbinder.unbind();
        if (mPresenter != null) mPresenter.detachVM();
    }

    protected abstract int getLayoutId();

    protected abstract void viewCreate(Bundle savedInstanceState);

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
//        FragmentAnimator fragmentAnimator = _mActivity.getFragmentAnimator();
//        fragmentAnimator.setEnter(0);
//        fragmentAnimator.setExit(0);
//        return fragmentAnimator;
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
            return new DefaultVerticalAnimator();
        else
            // 设置横向(和安卓4.x动画相同)
            return new DefaultHorizontalAnimator();
    }


    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
//            _mBackToFirstListener.onBackToFirstFragment();
            _mActivity.finish();
        }
        return true;
    }

//    public boolean addRxStop(Disposable disposable) {
//        if (disposablesStop == null) {
//            throw new IllegalStateException(
//                    "addUtilStop should be called between onStart and onStop");
//        }
//        disposablesStop.add(disposable);
//        return true;
//    }
//
//    public boolean addRxDestroy(Disposable disposable) {
//        if (disposablesDestroy == null) {
//            throw new IllegalStateException(
//                    "addUtilDestroy should be called between onCreate and onDestroy");
//        }
//        disposablesDestroy.add(disposable);
//        return true;
//    }
//
//    public void remove(Disposable disposable) {
//        if (disposablesStop == null && disposablesDestroy == null) {
//            throw new IllegalStateException("remove should not be called after onDestroy");
//        }
//        if (disposablesStop != null) {
//            disposablesStop.remove(disposable);
//        }
//        if (disposablesDestroy != null) {
//            disposablesDestroy.remove(disposable);
//        }
//    }
//
//    public void onStart() {
//        super.onStart();
//        if (disposablesStop != null) {
//            throw new IllegalStateException("onStart called multiple times");
//        }
//        disposablesStop = new ListCompositeDisposable();
//    }
//
//    public void onStop() {
//        super.onStop();
//        if (disposablesStop == null) {
//            throw new IllegalStateException("onStop called multiple times or onStart not called");
//        }
//        disposablesStop.dispose();
//        disposablesStop = null;
//    }
//
//    public void onDestroy() {
//        super.onDestroy();
//        if (disposablesDestroy == null) {
//            throw new IllegalStateException(
//                    "onDestroy called multiple times or onCreate not called");
//        }
//        disposablesDestroy.dispose();
//        disposablesDestroy = null;
//    }

}
