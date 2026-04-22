package com.cgr.codrinterraerp.model.request;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private String username, password, deviceModel, deviceId, fcmToken, androidVersion, appVersion;
    private int originId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    @NonNull
    @Override
    public String toString() {
        return "LoginRequest{" +
                "originId=" + originId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", androidVersion='" + androidVersion + '\'' +
                ", fcmToken='" + fcmToken + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                '}';
    }
}