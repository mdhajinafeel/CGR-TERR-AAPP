package com.cgr.codrinterraerp.model.response.syncdata;

import java.io.Serializable;

public class ContainerDataMappingsResponse implements Serializable {

    public String tempReceptionDataId, tempDispatchId, containerReceptionMappingId, tempReceptionId;
    public int dispatchId, dispatchDataId, receptionDataId, receptionId;
}