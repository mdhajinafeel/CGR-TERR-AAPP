package com.cgr.codrinterraerp.utils;

import android.util.Log;

import com.cgr.codrinterraerp.BuildConfig;

public class AppLogger {

    private AppLogger() {
        // Prevent instantiation
    }

    private static void log(int level, Class<?> cls, String message, Throwable throwable) {
        if (!BuildConfig.DEBUG) return;

        String tag = cls.getSimpleName();

        if (throwable != null) {
            Log.println(level, tag, message + '\n' + Log.getStackTraceString(throwable));
        } else {
            Log.println(level, tag, message);
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