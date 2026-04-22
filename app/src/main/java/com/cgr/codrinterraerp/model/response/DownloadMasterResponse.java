package com.cgr.codrinterraerp.model.response;

import java.io.Serializable;

public class DownloadMasterResponse implements Serializable {

    private boolean status;
    private String message;
    private int version;
    private DownloadMasterDataResponse data;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DownloadMasterDataResponse getData() {
        return data;
    }

    public void setData(DownloadMasterDataResponse data) {
        this.data = data;
    }
}