package com.cgr.codrinterraerp.firebase;

import com.cgr.codrinterraerp.R;

import java.util.HashMap;
import java.util.Map;

public class NotificationStringMapper {

    public static final Map<String, Integer> STRING_MAP = new HashMap<>();

    static {

        // =================================
        // SYNC
        // =================================
        STRING_MAP.put("sync_completed", R.string.sync_completed);
        STRING_MAP.put("sync_failed", R.string.sync_failed);
        STRING_MAP.put("data_sync_successfully", R.string.data_sync_successfully);
    }
}