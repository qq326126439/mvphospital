package com.example.gongshihao.myapplication.WebService;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

import com.example.gongshihao.myapplication.Constracts.NetworkConstracts;
import com.example.gongshihao.myapplication.Util.WebServiceUtils;
import com.example.gongshihao.myapplication.bean.User;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.ksoap2.serialization.SoapObject;

public class XWebService {
    /**
     * @param tClass Json解析类
     * @param methodName webservice 调用方法
     * @param arrayMap webservice 方法参数
     * @param  onResultListener 回调方法
    *
    * */
    public static <T> void getIntentData(final Class<T> tClass, final String methodName, final SimpleArrayMap<String, String> arrayMap, final OnResultListener<T> onResultListener) {
//        SimpleArrayMap<String, String> arrayMap = null;
//        if (json != null) {
//            arrayMap = new SimpleArrayMap<>();
//            arrayMap.put("args", json);
//        }
        WebServiceUtils.create().call(NetworkConstracts.WebServiceUrl, NetworkConstracts.WebServiceNameSpace, methodName, arrayMap, new WebServiceUtils.Response() {
            @Override
            public void onSuccess(SoapObject result) {
                if (result != null) {
                    String mJson = result.getProperty(0).toString();
//                    Log.e("XWebService", "服务端获取的JSON---->" + methodName + "---->" + mJson);
                    Gson gson = new Gson();
                    try {
                        T bean = gson.fromJson(mJson, tClass);
                        onResultListener.onSuccess(bean);
                    }catch (JsonParseException e){
                        onResultListener.onError();
                    }


                }
            }

            @Override
            public void onError(Exception e) {
                onResultListener.onError();
            }
        });

    }
    public static void TestConnect(final String methodName,final OnResultListener<String> onResultListener){
        WebServiceUtils.create().call(User.getUser().Url,NetworkConstracts.WebServiceNameSpace,methodName,null,new WebServiceUtils.Response(){

            @Override
            public void onSuccess(SoapObject result) {
                if (result != null) {
                    String mJson = result.getProperty(0).toString();
//                    Log.e("XWebService", "服务端获取的JSON---->" + methodName + "---->" + mJson);
                    onResultListener.onSuccess(mJson);


                }
            }

            @Override
            public void onError(Exception e) {
                    onResultListener.onError();
            }
        });

    }

    /***
     *
     *
     */
    public interface OnResultListener<T> {
        //请求成功回调
        void onSuccess(T bean);
        //请求失败回调
        void onError();
    }

}
