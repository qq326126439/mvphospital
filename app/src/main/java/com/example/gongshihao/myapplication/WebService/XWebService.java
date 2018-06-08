package com.example.gongshihao.myapplication.WebService;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

import com.example.gongshihao.myapplication.Constracts.NetworkConstracts;
import com.example.gongshihao.myapplication.Util.WebServiceUtils;
import com.google.gson.Gson;

import org.ksoap2.serialization.SoapObject;

public class XWebService {
    /**
     * @param tClass Json解析类
     * @param methodName webservice 调用方法
     * @param json webservice 方法参数
     * @param  onResultListener 回调方法
    *
    * */
    public static <T> void getIntentData(final Class<T> tClass, final String methodName, final String json, final OnResultListener<T> onResultListener) {
        SimpleArrayMap<String, String> arrayMap = null;
        if (json != null) {
            arrayMap = new SimpleArrayMap<>();
            arrayMap.put("args", json);
        }
        WebServiceUtils.create().call(NetworkConstracts.WebServiceUrl, NetworkConstracts.WebServiceNameSpace, methodName, arrayMap, new WebServiceUtils.Response() {
            @Override
            public void onSuccess(SoapObject result) {
                if (result != null) {
                    String mJson = result.getProperty(0).toString();
//                    Log.e("XWebService", "服务端获取的JSON---->" + methodName + "---->" + mJson);
                    Gson gson = new Gson();
                    T bean = gson.fromJson(mJson, tClass);
                    onResultListener.onSuccess(bean);
                }
            }

            @Override
            public void onError(Exception e) {
                onResultListener.onError();
            }
        });

    }

    public interface OnResultListener<T> {

        void onSuccess(T bean);

        void onError();
    }

}
