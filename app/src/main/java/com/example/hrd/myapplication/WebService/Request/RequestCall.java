package com.example.hrd.myapplication.WebService.Request;

import com.example.hrd.myapplication.WebService.WebServiceUtil;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;

public class RequestCall
{
    private OkHttpRequest okHttpRequest;
    private SoapSerializationEnvelope request;
    private long readTimeOut;
    private long writeTimeOut;
    private long connTimeOut;

    private OkHttpClient clone;

    public RequestCall(OkHttpRequest request)
    {
        this.okHttpRequest = request;
    }

    public RequestCall readTimeOut(long readTimeOut)
    {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public RequestCall writeTimeOut(long writeTimeOut)
    {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    public RequestCall connTimeOut(long connTimeOut)
    {
        this.connTimeOut = connTimeOut;
        return this;
    }

    public void buildcall()
    {
        request = generateRequest();
//        return request;
    }

    private SoapSerializationEnvelope generateRequest()
    {
        return okHttpRequest.generateRequest();
    }

    public Observable<String> execute()
    {
        buildcall();
        return WebServiceUtil.getInstance().execute(request);
    }

}
