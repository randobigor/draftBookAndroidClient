package com.randob.draft;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("draft")
    Call<List<Draft>> getDrafts();

    @GET("draft/{id}")
    Call<Draft> getDraftById(@Path("id") int id);

    @POST("draft")
    Call<Draft> createDraft(@Body Draft draft);

    @PUT("draft/{id}")
    Call<Draft> updateDraft(@Path("id") int id, @Body Draft draft);
}
