package com.it5.retrofit_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.it5.retrofit_demo.phone.PhoneApi;
import com.it5.retrofit_demo.phone.PhoneResult;
import com.it5.retrofit_demo.phone.PhoneService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by IT5 on 2016/6/21.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.phone_view)
    EditText phoneView;
    @BindView(R.id.result_view)
    TextView resultView;

    private PhoneApi phoneApi;
    private PhoneService phoneService;

    private Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=this;
        ButterKnife.bind(this);

        phoneApi=PhoneApi.getApi();
        phoneService=phoneApi.getService();

    }

    @OnClick({R.id.query_view, R.id.query_rxjava_view, R.id.duo_shuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query_view:
                query();
                break;
            case R.id.query_rxjava_view:
                queryByRxjava();
                break;
            case R.id.duo_shuo:
                startActivity(new Intent(activity,DuoShuoActivity.class));
                break;
        }
    }

    private void queryByRxjava() {
        resultView.setText("");
        String number=phoneView.getText().toString();
        if (number.isEmpty()){
            Toast.makeText(activity, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }

        phoneService.getPhoneResult(PhoneApi.API_KEY,number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhoneResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PhoneResult result) {
                        if (result!=null&&result.getErrNum()==0){
                            PhoneResult.RetDataEntity entity=result.getRetData();
                            resultView.append("地址：" + entity.getCity());
                        }
                    }
                });

    }

    private void query() {
        resultView.setText("");
        String number=phoneView.getText().toString();
        if (number.isEmpty()){
            Toast.makeText(activity, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<PhoneResult>call=phoneService.getResult(PhoneApi.API_KEY,number);
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if (response.isSuccessful()){
                    PhoneResult result=response.body();
                    if (result!=null&&result.getErrNum()==0){
                        PhoneResult.RetDataEntity entity=result.getRetData();
                        resultView.append("地址：" + entity.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });

    }

}
