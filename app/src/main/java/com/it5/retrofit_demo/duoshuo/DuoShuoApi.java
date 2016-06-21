package com.it5.retrofit_demo.duoshuo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IT5 on 2016/6/21.
 */
public class DuoShuoApi {
    public static DuoShuoApi getApi(){
        return SingleHodle.duoShuoApi;
    }

    private static class SingleHodle{
        public static DuoShuoApi duoShuoApi=new DuoShuoApi();
    }

    private DuoShuoService service;

    public DuoShuoApi() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://api.duoshuo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(DuoShuoService.class);

    }

    public DuoShuoService getService() {
        return service;
    }
}
