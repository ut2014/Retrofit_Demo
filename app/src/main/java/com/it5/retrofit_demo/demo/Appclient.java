package com.it5.retrofit_demo.demo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by IT5 on 2016/6/21.
 */
public class Appclient {
    static Retrofit retrofit;

    public static Retrofit retrofit() {
        if (retrofit == null) {
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://www.weather.com.cn/")
//                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public interface ApiStores{
        @GET("adat/sk/{cityId}.html")
        Call<WeatherJson> getWeather(@Path("cityId")String cityId);
    }
}
