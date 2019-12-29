package com.app.retrofitapp.Retrofit;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getInstance(){


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(22,TimeUnit.SECONDS)
                .readTimeout(22, TimeUnit.SECONDS)
                .writeTimeout(22, TimeUnit.SECONDS)
                .build();

        if(retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://bookbudiapp.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

        return retrofit;



    }

    private RetrofitClient(){

    }


}
