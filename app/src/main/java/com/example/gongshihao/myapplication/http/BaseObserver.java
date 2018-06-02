package com.example.gongshihao.myapplication.http;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.util.Log;
import android.widget.Toast;

import com.example.gongshihao.myapplication.MyApplication;
import com.example.gongshihao.myapplication.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Jacky on 2017/5/24.
 */

public abstract class BaseObserver<T> implements Observer<HttpResult<T>> {
    private final String TAG = BaseObserver.class.getSimpleName();
    private final int RESPONSE_CODE_OK = 1;       //自定义的业务逻辑，成功返回积极数据
    private final int RESPONSE_CODE_FAILED = -1;  //返回数据失败,严重的错误

    private Context mContext;
    private static Gson gson = new Gson();

    private int errorCode = -1111;
    private String errorMsg = "未知的错误！";

    Disposable mDisposable;

    /**
     * 根据具体的Api 业务逻辑去重写 onSuccess 方法！Error 是选择重写，but 必须Super ！
     *
     * @param t
     */
    public abstract void onSuccess(T t);


    //    /**
//     * @param
//     */
    public BaseObserver() {
        super();
//        StyledDialog.buildLoading( "加载中...").show();
    }

    /**
     * @param context
     */
    public BaseObserver(Context context) {
        this.mContext = context;
//        HttpUiTips.showDialog(mContext, true, null);
//        StyledDialog.buildLoading("加载中...").show();
    }

    /**
     * @param mCtx
     * @param showProgress 默认需要显示进程，不要的话请传 false
     */
    public BaseObserver(Context mCtx, boolean showProgress) {
        this.mContext = mCtx;
        if (showProgress) {
//            StyledDialog.buildLoading("加载中...").show();
//            HttpUiTips.showDialog(mContext, true, null);
        }
    }

    @Override
    public final void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public final void onNext(HttpResult<T> response) {
        Log.e(TAG, response.toString());

        Log.e("返回的数据---", response.getCode() + "/" + response.getData());
//        StyledDialog.dismissLoading();
        if (response.getCode() == RESPONSE_CODE_OK) {
            if (response.getData() != null) {
                if (response.isFollow()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("data", response.getData());
                    map.put("follow", response.isFollow());
                    onSuccess(map);

                } else {
                    onSuccess(response.getData());
                }
            } else
                onSuccess(response.getMessage());

        } else {
            onFailure(response.getCode(), response.getMessage());
        }
    }

    @CallSuper
    public void onSuccess(String message) {

    }

    @CallSuper
    public void onSuccess(Map map) {

    }

    @Override
    public final void onError(Throwable t) {
//        StyledDialog.dismissLoading();
        Log.e("错误日志", t.toString());
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            errorCode = httpException.code();
            errorMsg = httpException.getMessage();
            getErrorMsg(httpException);
        } else if (t instanceof SocketTimeoutException) {  //VPN open
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = MyApplication.getContext().getString(R.string.str_network_timeout);
        } else if (t instanceof ConnectException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = MyApplication.getContext().getString(R.string.str_network_check);
        } else if (t instanceof RuntimeException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "运行时错误";
        } else if (t instanceof UnknownHostException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "无法解析主机，请检查网络连接";
        } else if (t instanceof UnknownServiceException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = MyApplication.getContext().getString(R.string.str_network_unknownserver);
        } else if (t instanceof IOException) {  //飞行模式等
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = MyApplication.getContext().getString(R.string.str_network_nointernet);
        }
        onFailure(errorCode, errorMsg);  //
        if (mDisposable != null)
            mDisposable.dispose();
    }

    /**
     * 简单的把Dialog 处理掉
     */
    @Override
    public final void onComplete() {
//        StyledDialog.dismissLoading();
//        mDisposable.dispose();
    }

    /**
     * Default error dispose!
     * 一般的就是 AlertDialog 或 SnackBar
     *
     * @param code
     * @param message
     */
    @CallSuper
    public void onFailure(int code, String message) {
        if (code == RESPONSE_CODE_FAILED && mContext != null) {
//            HttpUiTips.alertTip(mContext, message, code);
        } else {
            disposeEorCode(message, code);
        }
    }

    /**
     * 对通用问题的统一拦截处理
     *
     * @param code
     */
    private final void disposeEorCode(String message, int code) {
        switch (code) {
            case 101:
            case 112:
            case 123:
            case 401:
//                //退回到登录页面
//                if (mContext != null && !((Activity) mContext).isFinishing()) {
//                    Intent intent = new Intent();
//                    intent.setClass(mContext, LoginActivity.class);
//                    mContext.startActivity(intent);
//                }
                break;
        }
        if (mContext != null) {
            Toast.makeText(mContext, message + "   code=" + code, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 获取详细的错误的信息 errorCode,errorMsg
     * <p>
     * 以登录的时候的Grant_type 故意写错为例子,这个时候的http 应该是直接的返回401=httpException.code()
     * 但是是怎么导致401的？我们的服务器会在respose.errorBody 中的content 中说明
     */
    private final void getErrorMsg(HttpException httpException) {
        String errorBodyStr = "";
        try {   //我们的项目需要的UniCode转码，不是必须要的！
            errorBodyStr = httpException.response().errorBody().string();
        } catch (IOException ioe) {
            Log.e("errorBodyStr ioe:", ioe.toString());
        }
        try {
            HttpResult errorResponse = gson.fromJson(errorBodyStr, HttpResult.class);
            if (null != errorResponse) {
                errorCode = errorResponse.getCode();
                errorMsg = errorResponse.getMessage();
            }
//            else {
//                errorCode = RESPONSE_CODE_FAILED;
//                errorMsg = "ErrorResponse is null";
//            }
        } catch (Exception jsonException) {
//            errorCode = RESPONSE_CODE_FAILED;
//            errorMsg = "http请求错误Json 信息异常";
            jsonException.printStackTrace();
        }
    }
}
