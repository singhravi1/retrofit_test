package com.startxlabs.rest_api;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Dayin on 02/03/16.
 */
public class APIClient {
    private static APIClient instance;
    private static OkHttpClient client;
    private String baseUrl = "http://aeapp.herokuapp.com/";

    public static synchronized APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();

        }
        return instance;
    }

    public IArcfixApi getRestAdapter() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // .client(client)
                .build();
        return restAdapter.create(IArcfixApi.class);
    }
}
