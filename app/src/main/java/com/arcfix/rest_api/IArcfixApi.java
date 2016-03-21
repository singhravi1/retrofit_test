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

    @GET("api/v1/orgs/56eff486ce06f403008fff66/users/me/feed")
    Call<MainResponse>  getMainFeeds();



}
