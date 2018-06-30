package com.example.hrd.myapplication.WebService;

import com.example.hrd.myapplication.Constracts.NetworkConstracts;
import com.example.hrd.myapplication.Util.TUtil;
import com.example.hrd.myapplication.WebService.builder.PostFormBuilder;
import com.example.hrd.myapplication.bean.User;
import com.google.gson.Gson;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class WebServiceUtil {
    private volatile static WebServiceUtil mInstance;
    private HttpTransportSE HttpTransportSE;


    public WebServiceUtil(HttpTransportSE httpTransportSE)
    {
        if (httpTransportSE == null)
        {
            this.HttpTransportSE = new HttpTransportSE(User.getUser().getUrl(), NetworkConstracts.DEFAULT_TIMEOUT);
        } else
        {
            this.HttpTransportSE = httpTransportSE;
        }
        HttpTransportSE.debug=false;
    }

    public static WebServiceUtil initClient(HttpTransportSE httpTransportSE)
    {
        if (mInstance == null)
        {
            synchronized (WebServiceUtil.class)
            {
                if (mInstance == null)
                {
                    mInstance = new WebServiceUtil(httpTransportSE);
                }
            }
        }
        return mInstance;
    }

    public static WebServiceUtil getInstance()
    {
        return initClient(null);
    }

    public static PostFormBuilder post()
    {
        return new PostFormBuilder();
    }

    public Observable<String> execute(final SoapSerializationEnvelope Envelope){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e){

                try {
                    HttpTransportSE.call(null,Envelope);
                    if(Envelope.getResponse()!=null) {
                        SoapObject result = (SoapObject) Envelope.bodyIn;
                        String mJson = result.getProperty(0).toString();
                        e.onNext(mJson);

                    }
                } catch (IOException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                } catch (XmlPullParserException e1) {
                    e1.printStackTrace();
                }

            }
        });

    }

}
