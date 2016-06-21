package com.it5.retrofit_demo.phone;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by IT5 on 2016/6/21.
 */
public interface PhoneService {
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(@Header("apikey") String apikey,
                                @Query("phone") String phone);

    @GET("/apistore/mobilenumber/mobilenumber")
    Observable<PhoneResult> getPhoneResult(@Header("apikey") String apikey,
                                           @Query("phone") String phone);

}
