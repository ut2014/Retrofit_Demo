package com.it5.retrofit_demo.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.it5.retrofit_demo.R;
import com.it5.retrofit_demo.interf.ApiStores;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DemoActivity extends AppCompatActivity {

    private DemoActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_duo_shuo);
        getWeather1();
//        getWeather();


    }

    private void getWeatherRX() {
        Appclient.ApiStores apiStores = Appclient.retrofit().create(Appclient.ApiStores.class);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nuuneoi.com/base/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


    }

    private void getWeather() {
        Appclient.ApiStores apiStores = Appclient.retrofit().create(Appclient.ApiStores.class);
        Call<WeatherJson> call = apiStores.getWeather("101010100");
        call.enqueue(new Callback<WeatherJson>() {
            @Override
            public void onResponse(Call<WeatherJson> call, Response<WeatherJson> response) {
                Toast.makeText(activity, response.body().toString(), Toast.LENGTH_SHORT).show();
                ;
            }

            @Override
            public void onFailure(Call<WeatherJson> call, Throwable t) {

            }
        });
    }


    private void getWeather1() {
        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl("http://www.weather.com.cn/")
                .build();
        ApiStores apiStores = retrofit.create(ApiStores.class);
        final Call<ResponseBody> call = apiStores.getWeather("101010100");
/*        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Response<ResponseBody> bodyResponse = call.execute();
                            String body = bodyResponse.body().string();//获取返回体的字符串
                            Log.i("wxl", "body=" + body);
//                            Toast.makeText(this,body,Toast.LENGTH_SHORT).show();
                        }catch (IOException e){

                        }
                    }
                }
        ).start();*/

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.i("wxl", "response=" + result);
                    Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
