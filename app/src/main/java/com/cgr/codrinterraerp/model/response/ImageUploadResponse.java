package com.cgr.codrinterraerp.model.response;

import java.io.Serializable;

public class ImageUploadResponse implements Serializable {

    public boolean status;
    public String message;
    public String url;
    public String tempContainerImageId;
    public String tempDispatchId;
}