package com.example.cbluser3.jsonparse.webservice;

import com.example.cbluser3.jsonparse.utils.Constants;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by cbluser24 on 8/2/17.
 */

public class RestClient {
    private static API REST_API;


    public static API getClient()
    {
        if(REST_API==null)
        {
            createClient();
        }
    return REST_API;
    }



    private static void createClient()
    {
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.setConnectTimeout(1, TimeUnit.MINUTES);

        Retrofit.Builder mBuider=new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);

          REST_API=mBuider.build().create(API.class);
    }
}
