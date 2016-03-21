package com.arcfix.rest_api;

import com.arcfix.rest_api.data_model.MainResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by zayin on 02/03/16.
 */
public interface IArcfixApi {

    @GET("api/v1/orgs/56edbcbf7330d803004a7f2a/users/me/feed")
    Call<MainResponse>  getMainFeeds();



}
