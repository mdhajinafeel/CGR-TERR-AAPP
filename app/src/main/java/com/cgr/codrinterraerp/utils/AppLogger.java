package com.cgr.codrinterraerp.utils;

import android.util.Log;

import com.cgr.codrinterraerp.BuildConfig;
import com.cgr.codrinterraerp.db.dao.ApiLogsDao;
import com.cgr.codrinterraerp.db.entities.ApiLogs;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppLogger {

    private static ApiLogsDao apiLogsDao;
    private static final Executor executor = Executors.newSingleThreadExecutor();

    private AppLogger() {
        // Prevent instantiation
    }

    // 🔥 INIT (call once)
    public static void init(ApiLogsDao dao) {
        apiLogsDao = dao;
    }

    private static void log(int level, Class<?> cls, String message, Throwable throwable) {

        String tag = cls.getSimpleName();

        // ✅ Always log to Logcat in debug
        if (BuildConfig.DEBUG) {
            if (throwable != null) {
                Log.println(level, tag, message + '\n' + Log.getStackTraceString(throwable));
            } else {
                Log.println(level, tag, message);
            }
        }

        // 🔥 ALSO STORE IN DB (only for WARN & ERROR)
        if (apiLogsDao != null && (level == Log.ERROR || level == Log.WARN)) {
            executor.execute(() -> {
                try {
                    ApiLogs log = new ApiLogs();
                    log.createdAt = System.currentTimeMillis();

                    if (throwable != null) {

                        String stack = Log.getStackTraceString(throwable);

                        log.exceptionType = throwable.getClass().getSimpleName();
                        log.type = CommonUtils.classifyError(throwable);

                        if (stack.length() > 5000) {
                            stack = stack.substring(0, 5000) + "...";
                        }

                        log.errorMessage = throwable.getMessage();
                        log.responseBody = stack;
                    } else {
                        log.type = "ERROR";
                    }

                    log.tag = tag;
                    log.methodName = message;
                    log.success = false;
                    apiLogsDao.insertApiLogs(log);
                } catch (Exception e) {
                    Log.e("AppLogger", "Failed to store log", e);
                }
            });
        }
    }

    public static void d(Class<?> cls, String message) {
        log(Log.DEBUG, cls, message, null);
    }

    public static void i(Class<?> cls, String message) {
        log(Log.INFO, cls, message, null);
    }

    public static void w(Class<?> cls, String message) {
        log(Log.WARN, cls, message, null);
    }

    public static void e(Class<?> cls, String message) {
        log(Log.ERROR, cls, message, null);
    }

    public static void e(Class<?> cls, String message, Throwable throwable) {
        log(Log.ERROR, cls, message, throwable);
    }
}