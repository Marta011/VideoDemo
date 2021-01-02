package com.example.videodemo.service.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    private static final String BASE_URL="http://zryjto.linuxpl.info/zadanko-api/";
    private static MyRetrofit myRetrofit;
    private Retrofit retrofit;

    public MyRetrofit(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized MyRetrofit getInstance(){
        if (myRetrofit==null){
            myRetrofit=new MyRetrofit();
        }
        return myRetrofit;
    }
    public VideoAPI getVideoApi(){
        return retrofit.create(VideoAPI.class);
    }

}
