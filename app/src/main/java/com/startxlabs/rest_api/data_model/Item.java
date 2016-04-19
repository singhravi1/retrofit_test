package com.startxlabs.rest_api.data_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("_id")
    @Expose
    private String _Id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("residue")
    @Expose
    private String residue;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("story")
    @Expose
    private Story story;
    @SerializedName("dismissable")
    @Expose
    private Boolean dismissable;


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("basic")
    @Expose
    private Basic basic;


    public String get_Id() {
        return id;
    }

    public void set_Id(String _Id) {
        this.id = _Id;
    }

    /**
     * @return The Id
     */
    public String getId() {
        return _Id;
    }

    /**
     * @param Id The _id
     */
    public void setId(String Id) {
        this._Id = Id;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return The residue
     */
    public String getResidue() {
        return residue;
    }

    /**
     * @param residue The residue
     */
    public void setResidue(String residue) {
        this.residue = residue;
    }

    /**
     * @return The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return The story
     */
    public Story getStory() {
        return story;
    }

    /**
     * @param story The story
     */
    public void setStory(Story story) {
        this.story = story;
    }

    /**
     * @return The dismissable
     */
    public Boolean getDismissable() {
        return dismissable;
    }

    /**
     * @param dismissable The dismissable
     */
    public void setDismissable(Boolean dismissable) {
        this.dismissable = dismissable;
    }

    /**
     * @return The basic
     */
    public Basic getBasic() {
        return basic;
    }

    /**
     * @param basic The basic
     */
    public void setBasic(Basic basic) {
        this.basic = basic;
    }

}
