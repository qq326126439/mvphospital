package com.example.hrd.myapplication.WebService.builder;


import com.example.hrd.myapplication.WebService.Request.PostFormRequest;
import com.example.hrd.myapplication.WebService.Request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhy on 15/12/14.
 */
public class PostFormBuilder extends OkHttpRequestBuilder<PostFormBuilder> implements HasParamsable
{
    @Override
    public RequestCall build()
    {
        return new PostFormRequest(methodName,namespace, tag, params, headers,id).build();
    }



    @Override
    public PostFormBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }




}
