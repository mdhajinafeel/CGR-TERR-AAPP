package com.cgr.codrinterraerp.services;

import com.cgr.codrinterraerp.constants.SyncResult;

public interface SyncCallback {

    void onResult(SyncResult result);
}