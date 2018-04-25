package com.dikonia.samples.rgridview;

import android.app.Application;

import com.dikonia.samples.rgridview.retrofit.CallAPIs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dikonia on 24/04/2018.
 */

public class MyApplication extends Application
{

    Retrofit retrofit = null;

    CallAPIs ccWebServiceApi = null;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        ccWebServiceApi = retrofit.create(CallAPIs.class);

    }

    public CallAPIs getRetrofit()
    {
        return ccWebServiceApi;
    }

}
