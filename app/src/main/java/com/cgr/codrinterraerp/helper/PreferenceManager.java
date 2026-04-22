package com.cgr.codrinterraerp.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import devliving.online.securedpreferencestore.SecuredPreferenceStore;

@SuppressWarnings("unused")
public enum PreferenceManager {

    INSTANCE;

    private static final String KEY_ORIGIN_ICON = "originIcon";
    private static final String KEY_ORIGIN_ID = "originId";
    private static final String KEY_ORIGIN_NAME = "originName";
    private static final String KEY_LOGGED_IN = "loggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_LOGIN_DETAIL_ID = "loginDetailId";
    private static final String KEY_USER_ROLES = "userRoles";
    private static final String KEY_NAME = "name";
    private static final String KEY_CURRENCY_FORMAT = "currencyFormat";
    private static final String KEY_CURRENCY_EXCEL_FORMAT = "currencyExcelFormat";
    private static final String KEY_CURRENCY_NAME = "currencyName";
    private static final String KEY_TIMEZONE = "timeZone";
    private static final String KEY_LOGIN_EXPIRY = "loginExpiry";
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final String KEY_REFRESH_TOKEN = "refreshToken";
    private static final String KEY_PERMISSION_CAMERA_ASKED = "cameraPermissionAsked";
    private static final String KEY_PROFILE_PHOTO = "profilePhoto";
    private static final String KEY_FIREBASE_TOKEN = "fbToken";
    private static final String KEY_DOWNLOAD_MASTER_VERSION = "downloadMasterVersion";
    private static final String KEY_LAST_CLEANUP_TIME = "lastCleanupTime";

    private final SecuredPreferenceStore prefStore = SecuredPreferenceStore.getSharedInstance();

    // ===== Origin Id =====
    public void setOriginId(int originId) {
        prefStore.edit().putInt(KEY_ORIGIN_ID, originId).apply();
    }

    public int getOriginId() {
        return prefStore.getInt(KEY_ORIGIN_ID, 0);
    }

    // ===== Logged In =====
    public void setLoggedIn(boolean loggedIn) {
        prefStore.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
    }

    public boolean getLoggedIn() {
        return prefStore.getBoolean(KEY_LOGGED_IN, false);
    }

    // ===== Origin Name =====
    public void setOriginName(String originName) {
        prefStore.edit().putString(KEY_ORIGIN_NAME, originName).apply();
    }

    public String getOriginName() {
        return prefStore.getString(KEY_ORIGIN_NAME, "");
    }

    // ===== Origin Icon =====
    public void setOriginIcon(String originIcon) {
        prefStore.edit().putString(KEY_ORIGIN_ICON, originIcon).apply();
    }

    public String getOriginIcon() {
        return prefStore.getString(KEY_ORIGIN_ICON, "");
    }

    // ===== User Id =====
    public void setUserId(int userId) {
        prefStore.edit().putInt(KEY_USER_ID, userId).apply();
    }

    public int getUserId() {
        return prefStore.getInt(KEY_USER_ID, 0);
    }

    // ===== Currency Name =====
    public void setCurrencyName(String currencyName) {
        prefStore.edit().putString(KEY_CURRENCY_NAME, currencyName).apply();
    }

    public String getCurrencyName() {
        return prefStore.getString(KEY_CURRENCY_NAME, "");
    }

    // ===== Currency Format =====
    public void setCurrencyFormat(String currencyFormat) {
        prefStore.edit().putString(KEY_CURRENCY_FORMAT, currencyFormat).apply();
    }

    public String getCurrencyFormat() {
        return prefStore.getString(KEY_CURRENCY_FORMAT, "");
    }

    // ===== Currency Excel Format =====
    public void setCurrencyExcelFormat(String currencyExcelFormat) {
        prefStore.edit().putString(KEY_CURRENCY_EXCEL_FORMAT, currencyExcelFormat).apply();
    }

    public String getCurrencyExcelFormat() {
        return prefStore.getString(KEY_CURRENCY_EXCEL_FORMAT, "");
    }

    // ===== Name =====
    public void setName(String name) {
        prefStore.edit().putString(KEY_NAME, name).apply();
    }

    public String getName() {
        return prefStore.getString(KEY_NAME, "");
    }

    // ===== Login Expiry =====
    public void setLoginExpiry(Long expiry) {
        prefStore.edit().putLong(KEY_LOGIN_EXPIRY, expiry).apply();
    }

    public long getLoginExpiry() {
        return prefStore.getLong(KEY_LOGIN_EXPIRY, 0);
    }

    // ===== Timezone =====
    public void setTimeZone(String timeZone) {
        prefStore.edit().putString(KEY_TIMEZONE, timeZone).apply();
    }

    public String getTimeZone() {
        return prefStore.getString(KEY_TIMEZONE, "");
    }

    // ===== Profile Photo =====
    public void setProfilePhoto(String profilePhoto) {
        prefStore.edit().putString(KEY_PROFILE_PHOTO, profilePhoto).apply();
    }

    public String getProfilePhoto() {
        return prefStore.getString(KEY_PROFILE_PHOTO, "");
    }

    // ===== Access Token =====
    public void setAccessToken(String token) {
        prefStore.edit().putString(KEY_ACCESS_TOKEN, token).apply();
    }

    public String getAccessToken() {
        return prefStore.getString(KEY_ACCESS_TOKEN, "");
    }

    // ===== Refresh Token =====
    public void setRefreshToken(String token) {
        prefStore.edit().putString(KEY_REFRESH_TOKEN, token).apply();
    }

    public String getRefreshToken() {
        return prefStore.getString(KEY_REFRESH_TOKEN, "");
    }

    // ===== Firebase Token =====
    public String getFirebaseToken() {
        return prefStore.getString(KEY_FIREBASE_TOKEN, "");
    }

    public void setFirebaseToken(String firebaseToken) {
        prefStore.edit().putString(KEY_FIREBASE_TOKEN, firebaseToken);
    }

    // ===== Login Detail Id =====
    public int getLoginDetailId() {
        return prefStore.getInt(KEY_LOGIN_DETAIL_ID, 0);
    }

    public void setLoginDetailId(int loginDetailId) {
        prefStore.edit().putInt(KEY_LOGIN_DETAIL_ID, loginDetailId);
    }

    // ===== User Roles =====
    public Set<String> getUserRoles() {
        return new HashSet<>(prefStore.getStringSet(KEY_USER_ROLES, new HashSet<>()));
    }

    public void setUserRoles(String userRoles) {
        Set<String> rolesSet = new HashSet<>(Arrays.asList(userRoles.split(",")));
        prefStore.edit().putStringSet(KEY_USER_ROLES, rolesSet).apply();
    }

    // ===== Download Master Version =====
    public int getDownloadMasterVersion() {
        return prefStore.getInt(KEY_DOWNLOAD_MASTER_VERSION, 0);
    }

    public void setDownloadMasterVersion(int downloadMasterVersion) {
        prefStore.edit().putInt(KEY_DOWNLOAD_MASTER_VERSION, downloadMasterVersion).apply();
    }

    // ===== LAST CLEANUP TIME =====
    public long getLastCleanupTime() {
        return prefStore.getLong(KEY_LAST_CLEANUP_TIME, 0);
    }

    public void setLastCleanupTime(long lastCleanupTime) {
        prefStore.edit().putLong(KEY_LAST_CLEANUP_TIME, lastCleanupTime).apply();
    }

    public void clearLoginSession() {
        prefStore.edit()
                .remove(KEY_ACCESS_TOKEN)
                .remove(KEY_REFRESH_TOKEN)
                .remove(KEY_LOGIN_EXPIRY)
                .remove(KEY_LOGGED_IN)
                .remove(KEY_NAME)
                .remove(KEY_PROFILE_PHOTO)
                .remove(KEY_USER_ID)
                .remove(KEY_ORIGIN_ID)
                .remove(KEY_ORIGIN_NAME)
                .remove(KEY_ORIGIN_ICON)
                .remove(KEY_CURRENCY_EXCEL_FORMAT)
                .remove(KEY_CURRENCY_FORMAT)
                .remove(KEY_PERMISSION_CAMERA_ASKED)
                .remove(KEY_CURRENCY_NAME)
                .remove(KEY_TIMEZONE)
                .remove(KEY_LOGIN_DETAIL_ID)
                .remove(KEY_USER_ROLES)
                .remove(KEY_DOWNLOAD_MASTER_VERSION)
                .apply();
    }

    public boolean hasRole(String role) {
        return getUserRoles().contains(role);
    }
}