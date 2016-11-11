package com.stveo.stevebowling.budget.Models;
//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;
import com.stveo.stevebowling.budget.Components.Constants;

import java.util.Date;


/**
 * Created by stevebowling on 11/1/16.
 */

public class Account {

    @SerializedName("Id")
    private String id;

    @SerializedName("Email")
    private String email;

    @SerializedName("HasRegistered")
    private Boolean hasRegistered;

    @SerializedName("LoginProvider")
    private String loginProvider;

    @SerializedName("FullName")
    private String fullName;

    @SerializedName("AvatarBase64")
    private String avatarBase64;

    @SerializedName("LastCheckInLongitude")
    private Double lastLong;
    
    @SerializedName("LastCheckInLatitude")
    private Double lastLat;

    @SerializedName("LastCheckInDateTime")
    private String  lastCheckinDate;

    @SerializedName("Password")
    private String password;

    @SerializedName("NewPassword")
    private String newPassword;

    @SerializedName("OldPassword")
    private String oldPassword;

    @SerializedName("ConfirmPassword")
    private String confirmPassword;

    @SerializedName("ApiKey")
    private String apiKey;

    @SerializedName("Access_token")
    private String token;

    @SerializedName(".expires")
    private Date expiration;

    @SerializedName("GrantType")
    private String grantType;



    public Account() {
    }

    public Account(String id, String email, Boolean hasRegistered , String loginProvider, String fullName, String avatarBase64 , Double lastLong, Double lastLat , String lastCheckinDate) {
        this.avatarBase64 = avatarBase64;
        this.email = email;
        this.fullName = fullName;
        this.hasRegistered = hasRegistered;
        this.id = id;
        this.lastCheckinDate = lastCheckinDate;
        this.lastLat = lastLat;
        this.lastLong = lastLong;
        this.loginProvider = loginProvider;
    }

    public Account(String email, String fullName,String avatarBase64, String apiKey, String password) {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.avatarBase64 = Constants.IMAGE;
        this.apiKey = Constants.apiKey;

    }
    public Account(String grantType, String email, String password) {
        this.grantType = Constants.grantType;
        this.email = email;
        this.password = password;

    }

    public Account( String avatarBase64) {
            this.avatarBase64 = Constants.IMAGE;
            //this.fullName = fullName;
    }

    public Account(String fullName, String avatarBase64) {
        this.avatarBase64 = Constants.IMAGE;
        this.fullName = fullName;
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


    public String getLastCheckinDate() {
        return lastCheckinDate;
    }

    public void setLastCheckinDate(String lastCheckinDate) {
        this.lastCheckinDate = lastCheckinDate;
    }

    public Double getLastLat() {
        return lastLat;
    }

    public void setLastLat(Double lastLat) {
        this.lastLat = lastLat;
    }

    public Double getLastLong() {
        return lastLong;
    }

    public void setLastLong(Double lastLong) {
        this.lastLong = lastLong;
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
