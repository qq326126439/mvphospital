package com.example.gongshihao.myapplication.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jacky on 2017/5/24.
 */

public class RetrofitClient {

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;
    private final static int DEFAULT_TIMEOUT = 20;
    private static HttpService httpService;
    private static final String BASE_URL = "http://vapp.12584.cn/";

    //private static final String BASE_URL="http://192.168.1.111:8080/zdtp3/";
    public  RetrofitClient(){

//        okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .build();
//
//        retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()) //添加Gson转化器
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加rxjava生成observables的adapter
//                .build();


    }

    public static HttpService getHttpService() {
        if (httpService == null) {
            synchronized (RetrofitClient.class) {
                okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();

                retrofit = new Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()) //添加Gson转化器
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加rxjava生成observables的adapter
                        .build();

                httpService = retrofit.create(HttpService.class);
            }
        }
        return httpService;
    }
}
