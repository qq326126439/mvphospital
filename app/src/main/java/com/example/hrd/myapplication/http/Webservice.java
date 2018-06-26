package com.example.hrd.myapplication.http;

import com.google.gson.annotations.SerializedName;

public class Webservice <T>{
    private T data;
    private String state;
    @SerializedName("msc")
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
