package com.startxlabs.rest_api;

import com.startxlabs.rest_api.data_model.PinResponse;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Deep on 02/03/16.
 */
public interface IArcfixApi {

    @POST("api/create")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    Call<PinResponse> createUser(@Field("phone") String phone, @Field("internationalCode")String countryCode);


}
