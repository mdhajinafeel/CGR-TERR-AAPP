package com.cgr.codrinterraerp.model.response;

import java.io.Serializable;

public class DownloadTransactionsResponse implements Serializable {

    private boolean status;
    private String message;
    private long serverTime;
    private DownloadTransactionsDataResponse data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DownloadTransactionsDataResponse getData() {
        return data;
    }

    public void setData(DownloadTransactionsDataResponse data) {
        this.data = data;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }
}