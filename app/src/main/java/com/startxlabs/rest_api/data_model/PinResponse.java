package com.startxlabs.rest_api.data_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deep on 06/07/16.
 */
public class PinResponse {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("pin")
    @Expose
    private String pin;

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The pin
     */
    public String getPin() {
        return pin;
    }

    /**
     *
     * @param pin
     * The pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

}