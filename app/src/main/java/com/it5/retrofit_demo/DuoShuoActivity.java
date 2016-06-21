package com.it5.retrofit_demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.it5.retrofit_demo.duoshuo.CommitParam;
import com.it5.retrofit_demo.duoshuo.CommitResult;
import com.it5.retrofit_demo.duoshuo.DuoShuoApi;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DuoShuoActivity extends Activity {

    private static final String TAG=DuoShuoActivity.class.getName();
    private Activity activity;
    private DuoShuoApi duoShuoApi;
    private CommitParam commitParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_shuo);
        ButterKnife.bind(this);
        activity=this;
        duoShuoApi=DuoShuoApi.getApi();
        commitParam=new CommitParam();
        commitParam.setSecret("key");
        commitParam.setAuthor_email("xxx@163.com");
        commitParam.setAuthor_name("xxx");
        commitParam.setAuthor_url("http://www.devwiki.net");
        commitParam.setShort_name("devwiki");
        commitParam.setThread_key("2016/03/13/Gradle-Get-SVN-Version-Code/");
        commitParam.setMessage("test commit!!!");
    }

    @OnClick({R.id.create_commit_single, R.id.create_commit_map})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_commit_single:
                createSingle();
                break;
            case R.id.create_commit_map:
                createMap();
                break;
        }

    }

    private void createMap() {
        Map map=commitParam.createCommitParams();
        Call<CommitResult> call=duoShuoApi.getService().createCommit(map);
        call.enqueue(callback);
    }

    private void createSingle() {
        Call<CommitResult> call=duoShuoApi.getService().createCommit(
                commitParam.getSecret(),
                commitParam.getShort_name(),
                commitParam.getAuthor_email(),
                commitParam.getAuthor_name(),
                commitParam.getThread_key(),
                commitParam.getAuthor_url(),
                commitParam.getMessage()
        );
        call.enqueue(callback);

    }
    private Callback<CommitResult> callback=new Callback<CommitResult>() {
        @Override
        public void onResponse(Call<CommitResult> call, Response<CommitResult> response) {
            if (response.isSuccessful()){
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                Toast.makeText(activity, response.body().toString(), Toast.LENGTH_SHORT).show();
            } else {
                Log.e(TAG, "+++" + response.message());
            }
        }

        @Override
        public void onFailure(Call<CommitResult> call, Throwable t) {
            Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}