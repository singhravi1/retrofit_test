package com.startxlabs.rest_api.data_model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("dest")
    @Expose
    private String dest;

    @SerializedName("type")
    @Expose
    private String type;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The dest
     */
    public String getDest() {
        return dest;
    }

    /**
     * @param dest The dest
     */
    public void setDest(String dest) {
        this.dest = dest;
    }

}
