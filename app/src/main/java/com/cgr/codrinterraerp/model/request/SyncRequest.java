package com.cgr.codrinterraerp.model.request;

import com.cgr.codrinterraerp.db.entities.ContainerData;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;
import com.cgr.codrinterraerp.db.entities.ReceptionData;
import com.cgr.codrinterraerp.db.entities.ReceptionDetails;

import java.io.Serializable;
import java.util.List;

public class SyncRequest implements Serializable {

    public List<ReceptionDetails> receptionDetails;
    public List<ReceptionData> receptionData;
    public List<DispatchDetails> dispatchDetails;
    public List<ContainerData> containerData;

    // DELETED
    public List<ReceptionDetails> deletedReceptionDetails;
    public List<ReceptionData> deletedReceptionData;
    public List<DispatchDetails> deletedDispatchDetails;
    public List<ContainerData> deletedContainerData;
}