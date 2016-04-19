package com.startxlabs.rest_api.data_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Basic {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("links")
    @Expose
    private List<Link> links = new ArrayList<Link>();

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
     * @return The links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param links The links
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
