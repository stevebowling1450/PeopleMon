package com.stveo.stevebowling.budget.Models;
//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;
import com.stveo.stevebowling.budget.Components.Constants;

import java.util.Date;


/**
 * Created by stevebowling on 11/1/16.
 */

public class Account {

    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("hasRegistered")
    private Boolean hasRegistered;

    @SerializedName("loginProvider")
    private String loginProvider;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("avatarBase64")
    private String avatarBase64;

    @SerializedName("lastLong")
    private Integer lastLong;

    @SerializedName("lastLat")
    private Integer lastLat;

    @SerializedName("lastCheckinDate")
    private Boolean lastCheckinDate;

    @SerializedName("password")
    private String password;

    @SerializedName("newPassword")
    private String newPassword;

    @SerializedName("oldPassword")
    private String oldPassword;

    @SerializedName("confirmPassword")
    private String confirmPassword;

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("token")
    private String token;

    @SerializedName("expiration")
    private Date expiration;

    @SerializedName("grantType")
    private String grantType;



    public Account() {
    }

    public Account(String email, String fullName,String avatarBase64, String apiKey, String password) {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.avatarBase64 = Constants.avatar;
        this.apiKey = Constants.apiKey;

    }
    public Account(String grantType, String email, String password) {
        this.grantType = Constants.grantType;
        this.email = email;
        this.password = password;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasRegistered() {
        return hasRegistered;
    }

    public void setHasRegistered(Boolean hasRegistered) {
        this.hasRegistered = hasRegistered;
    }

    public String getLoginProvider() {
        return loginProvider;
    }

    public void setLoginProvider(String loginProvider) {
        this.loginProvider = loginProvider;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }

    public Integer getLastLong() {
        return lastLong;
    }

    public void setLastLong(Integer lastLong) {
        this.lastLong = lastLong;
    }

    public Integer getLastLat() {
        return lastLat;
    }

    public void setLastLat(Integer lastLat) {
        this.lastLat = lastLat;
    }

    public Boolean getLastCheckinDate() {
        return lastCheckinDate;
    }

    public void setLastCheckinDate(Boolean lastCheckinDate) {
        this.lastCheckinDate = lastCheckinDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
