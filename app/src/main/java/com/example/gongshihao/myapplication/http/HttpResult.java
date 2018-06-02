package com.example.gongshihao.myapplication.http;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jacky on 2017/5/24.
 */

public class HttpResult<T> {


    @SerializedName("follow")
    private boolean follow;
    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }


//    public String getFollow() {
//        return follow;
//    }
//
//    public void setFollow(String follow) {
//        this.follow = follow;
//    }
}
