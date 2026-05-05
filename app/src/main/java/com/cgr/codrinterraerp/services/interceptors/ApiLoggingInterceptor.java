package com.cgr.codrinterraerp.services.interceptors;

import androidx.annotation.NonNull;

import com.cgr.codrinterraerp.db.dao.ApiLogsDao;
import com.cgr.codrinterraerp.db.entities.ApiLogs;
import com.cgr.codrinterraerp.utils.AppLogger;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class ApiLoggingInterceptor implements Interceptor {

    private final ApiLogsDao apiLogDao;
    private static final Executor executor = Executors.newSingleThreadExecutor();

    private static final long MAX_RESPONSE_SIZE = 10_000_000L; // 10MB
    private static final int DB_TRUNCATE_LENGTH = 5000;

    public ApiLoggingInterceptor(ApiLogsDao apiLogDao) {
        this.apiLogDao = apiLogDao;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        long startTime = System.currentTimeMillis();

        String requestBodyString = "";

        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            requestBodyString = buffer.readUtf8();
        }

        requestBodyString = maskSensitive(requestBodyString);

        try {
            try (Response response = chain.proceed(request)) {
                long duration = System.currentTimeMillis() - startTime;

                ResponseBody responseBody = response.body();

                String fullBody;
                String logBody;

                long contentLength = responseBody.contentLength();

                boolean isLarge = contentLength > MAX_RESPONSE_SIZE;
                boolean isSlow = duration > 2000;

                if (isLarge) {
                    logBody = "[Response too large: " + contentLength + " bytes | Skipped logging]";

                    AppLogger.w(ApiLoggingInterceptor.class,
                            "Large response: " + request.url() + " (" + contentLength + " bytes)");

                    logApiCall(request, requestBodyString, response.code(),
                            response.isSuccessful(), logBody, duration, isSlow, true);

                    return response.newBuilder().body(responseBody).build();
                }

                // Normal response
                fullBody = responseBody.string();
                logBody = fullBody;

                if (logBody.length() > DB_TRUNCATE_LENGTH) {
                    logBody = logBody.substring(0, DB_TRUNCATE_LENGTH) + "... [truncated]";
                }

                logBody = maskSensitive(logBody);

                logApiCall(request, requestBodyString, response.code(),
                        response.isSuccessful(), logBody, duration, isSlow, false);

                return response.newBuilder()
                        .body(ResponseBody.create(fullBody, responseBody.contentType()))
                        .build();
            }

        } catch (IOException e) {
            long duration = System.currentTimeMillis() - startTime;
            logFailedRequest(request, requestBodyString, e, duration);
            throw e;
        }
    }

    // ✅ Updated method (with flags)
    private void logApiCall(Request request, String requestBody, int statusCode,
                            boolean success, String responseBody, long duration,
                            boolean isSlow, boolean isLarge) {
        executor.execute(() -> {
            try {
                ApiLogs apiLog = new ApiLogs();
                apiLog.endpoint = shortUrl(request.url().toString());
                apiLog.method = request.method();
                apiLog.requestBody = requestBody;
                apiLog.responseBody = responseBody;
                apiLog.statusCode = statusCode;
                apiLog.success = success;
                apiLog.createdAt = System.currentTimeMillis();
                apiLog.durationMs = duration;

                // 🔥 flags
                apiLog.isSlow = isSlow;
                apiLog.isLarge = isLarge;
                apiLog.type = "API";
                apiLog.exceptionType = "";

                apiLogDao.insertApiLogs(apiLog);

                if (!success) {
                    AppLogger.w(ApiLoggingInterceptor.class, apiLog.method + " " + apiLog.endpoint + " returned " + statusCode + " in " + duration + "ms");
                }

            } catch (Exception e) {
                AppLogger.e(getClass(), "logApiCall", e);
            }
        });
    }

    private void logFailedRequest(Request request, String requestBody,
                                  IOException exception, long duration) {
        executor.execute(() -> {
            try {
                ApiLogs apiLog = new ApiLogs();
                apiLog.endpoint = shortUrl(request.url().toString());
                apiLog.method = request.method();
                apiLog.requestBody = requestBody;
                apiLog.responseBody = "";
                apiLog.statusCode = 0;
                apiLog.success = false;
                apiLog.errorMessage = exception.getMessage();
                apiLog.createdAt = System.currentTimeMillis();
                apiLog.durationMs = duration;
                apiLog.isSlow = duration > 2000;
                apiLog.isLarge = false;
                apiLog.type = "API";
                apiLog.exceptionType = "";

                apiLogDao.insertApiLogs(apiLog);

                AppLogger.e(ApiLoggingInterceptor.class,apiLog.method + " " + apiLog.endpoint + " failed: " + exception.getMessage(), exception);

            } catch (Exception e) {
                AppLogger.e(getClass(), "logFailedRequest", e);
            }
        });
    }

    private String maskSensitive(String body) {
        if (body == null || body.isEmpty()) {
            return "";
        }

        return body
                .replaceAll("\"password\"\\s*:\\s*\"[^\"]*\"", "\"password\":\"****\"")
                .replaceAll("\"deviceId\"\\s*:\\s*\"[^\"]*\"", "\"deviceId\":\"****\"")
                .replaceAll("\"accessToken\"\\s*:\\s*\"[^\"]*\"", "\"accessToken\":\"****\"")
                .replaceAll("\"refreshToken\"\\s*:\\s*\"[^\"]*\"", "\"refreshToken\":\"****\"");
    }

    private String shortUrl(String fullUrl) {
        try {
            String path = fullUrl.replaceFirst("https?://[^/]+", "");
            path = path.replace("/api/terraerp", "");

            // keep only last part
            if (path.contains("/")) {
                return path.substring(path.lastIndexOf("/"));
            }

            return path;
        } catch (Exception e) {
            return fullUrl;
        }
    }
}