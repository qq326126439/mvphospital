package com.example.hrd.myapplication.WebService.builder;



import com.example.hrd.myapplication.WebService.Request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder>
{
    protected String methodName;
    protected String namespace;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    protected int id;

    public T id(int id)
    {
        this.id = id;
        return (T) this;
    }
    public T methodName(String methodName){
        this.methodName=methodName;
        return (T) this;
    }
    public T namespace(String namespace){
        this.namespace=namespace;
        return (T) this;
    }



    public T tag(Object tag)
    {
        this.tag = tag;
        return (T) this;
    }

    public T headers(Map<String, String> headers)
    {
        this.headers = headers;
        return (T) this;
    }

    public T addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return (T) this;
    }
    public abstract RequestCall build();
}
