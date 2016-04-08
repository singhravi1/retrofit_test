package com.startxlabs.rest_api;

import com.startxlabs.rest_api.data_model.MainResponse;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Deep on 02/03/16.
 */
public interface IArcfixApi {

    @GET("api/v1/orgs/56eff486ce06f403008fff66/users/me/feed")
    Call<MainResponse>  getMainFeeds();



}
