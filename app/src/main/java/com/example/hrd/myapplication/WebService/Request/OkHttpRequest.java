package com.example.hrd.myapplication.WebService.Request;


import com.example.hrd.myapplication.WebService.Util.Exceptions;
import com.example.hrd.myapplication.bean.User;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;


public abstract class OkHttpRequest
{
    protected String methodName;
    protected String namespace;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected int id;
    protected SoapSerializationEnvelope request;
    protected OkHttpRequest(String methodName,String namespace, Object tag,
                            Map<String, String> params, Map<String, String> headers, int id)
    {
        this.methodName = methodName;
        this.namespace=namespace;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        this.id = id ;

        if (User.getUser().getUrl()==null||methodName == null||namespace==null)
        {
            Exceptions.illegalArgument("url,methodName or namespace can not be null.");
        }

        initBuilder();
    }



    /**
     * 初始化一些基本参数 url , tag , headers
     */
    private void initBuilder()
    {
        request=new SoapSerializationEnvelope(SoapEnvelope.VER11);

    }

    protected abstract SoapObject buildRequestBody();

//    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
//    {
//        return requestBody;
//    }

    protected abstract SoapSerializationEnvelope buildRequest(SoapObject requestBody);

    public RequestCall build()
    {
        return new RequestCall(this);
    }


    public SoapSerializationEnvelope generateRequest()
    {
        SoapObject object=buildRequestBody();
        SoapSerializationEnvelope request =buildRequest(object);
        return request;
    }


    protected void appendHeaders()
    {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet())
        {
            headerBuilder.add(key, headers.get(key));
        }
//        builder.headers(headerBuilder.build());
    }

    public int getId()
    {
        return id  ;
    }

}
