package com.cgr.codrinterraerp.model.response;

import java.io.Serializable;
import java.util.List;

public class SyncResponse implements Serializable {

    public boolean status;
    public String message;

    public List<IdMappingResponse> receptionMappings, receptionDataMappings, dispatchMappings;
}