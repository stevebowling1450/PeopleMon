package com.stveo.stevebowling.budget.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stevebowling on 11/7/16.
 */

public class User {



    @SerializedName("UserId")
    String userId;

    @SerializedName("UserName")
    String userName;

    @SerializedName("AvatarBase64")
    String avatarBase;

    @SerializedName("Longitude")
    double longitude;

    @SerializedName("Latitude")
    double latitude;

    @SerializedName("Created")
    String created;

    @SerializedName("CaughtUserId")
    String caughtUserId;

    @SerializedName("RadiusInMeters")
    Integer radiusInMeters;

    public User(String caughtUserId, Integer radiusInMeters) {
        this.caughtUserId = caughtUserId;
        this.radiusInMeters = radiusInMeters;
    }

    public User(String avatarBase, String created, double latitude, double longitude, String userId, String userName) {
        this.avatarBase = avatarBase;
        this.created = created;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;
        this.userName = userName;
    }

    public String getCaughtUserId() {
        return caughtUserId;
    }

    public void setCaughtUserId(String caughtUserId) {
        this.caughtUserId = caughtUserId;
    }

    public Integer getRadiusInMeters() {
        return radiusInMeters;
    }

    public void setRadiusInMeters(Integer radiusInMeters) {
        this.radiusInMeters = radiusInMeters;
    }

    public String getAvatarBase() {
        return avatarBase;
    }

    public void setAvatarBase(String avatarBase) {
        this.avatarBase = avatarBase;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
