package com.startxlabs.rest_api.data_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Story {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
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
     * @return The media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * @param media The media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
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
