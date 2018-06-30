package com.example.hrd.myapplication.WebService.Request;


import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;


public class PostFormRequest extends OkHttpRequest
{
    public PostFormRequest(String methodName,String namespace, Object tag, Map<String, String> params, Map<String, String> headers, int id)
    {
        super(methodName, namespace, tag, params, headers,id);
    }


    @Override
    protected SoapObject buildRequestBody() {
        SoapObject soapObject=new SoapObject(namespace,methodName);
        addParams(soapObject);
        return soapObject;
    }

    @Override
    protected SoapSerializationEnvelope buildRequest(SoapObject requestBody) {
        request.dotNet = false; // 设置是否调用的是.Net开发的WebService
        request.setOutputSoapObject(requestBody);
        return request;
    }

    private void addParams(MultipartBody.Builder builder)
    {
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, params.get(key)));
            }
        }
    }

    private void addParams(SoapObject builder)
    {
        if (params != null)
        {
            for (String key : params.keySet())
            {
                builder.addProperty(key,params.get(key));
            }
        }
    }

}
