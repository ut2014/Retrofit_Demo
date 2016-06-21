package com.it5.retrofit_demo.duoshuo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by IT5 on 2016/6/21.
 */
public interface DuoShuoService {

    @FormUrlEncoded
    @POST("/posts/create.json")
    Call<CommitResult> createCommit(
            @Field("secret") String secret,
            @Field("short_name") String shortName,
            @Field("author_email") String authorEmail,
            @Field("author_name") String authorName,
            @Field("thread_key") String threadKey,
            @Field("author_url") String author_url,
            @Field("message") String message
    );
    @FormUrlEncoded
    @POST("/posts/create.json")
    Call<CommitResult> createCommit(@FieldMap Map<String, Object> map);
}
