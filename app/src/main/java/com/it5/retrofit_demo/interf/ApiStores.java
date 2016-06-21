package com.it5.retrofit_demo.interf;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by IT5 on 2016/6/20.
 */
public interface ApiStores {
    @GET("adat/sk/{cityId}.html")
    Call<ResponseBody> getWeather(@Path("cityId") String cityId);
}
