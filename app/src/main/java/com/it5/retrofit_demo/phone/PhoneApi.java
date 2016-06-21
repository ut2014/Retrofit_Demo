package com.it5.retrofit_demo.phone;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 手机号相关的API
 * Created by IT5 on 2016/6/21.
 */
public class PhoneApi {
    public static final String BASE_URL="http://apis.baidu.com";
    public static final String API_KEY="8e13586b86e4b7f3758ba3bd6c9c9135";

    public static PhoneApi getApi(){
        return ApiHolder.phoneApi;
    }

    static class ApiHolder{
        private static PhoneApi phoneApi=new PhoneApi();
    }

    private PhoneService service;

    public PhoneApi() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(PhoneService.class);
    }

    /**
     * 获取PhoneService实例
     */
    public  PhoneService getService(){
        return service;
    }
}
