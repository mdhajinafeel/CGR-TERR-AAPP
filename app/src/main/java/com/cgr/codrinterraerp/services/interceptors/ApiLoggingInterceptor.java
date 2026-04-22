package com.cgr.codrinterraerp.services.interceptors;

import androidx.annotation.NonNull;

import com.cgr.codrinterraerp.db.dao.ApiLogsDao;
import com.cgr.codrinterraerp.db.entities.ApiLogs;

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

        try (Response response = chain.proceed(request)) {

            long duration = System.currentTimeMillis() - startTime;

            ResponseBody responseBody = response.body();

            String fullBody = responseBody.string();

            // 🔥 SAFE COPY FOR LOGGING ONLY
            String logBody = fullBody;
            if (logBody.length() > 5000) {
                logBody = logBody.substring(0, 5000); // only for DB
            }

            ApiLogs apiLog = new ApiLogs();
            apiLog.endpoint = request.url().toString();
            apiLog.method = request.method();
            apiLog.requestBody = requestBodyString;
            apiLog.responseBody = logBody; // ✅ truncated only here
            apiLog.statusCode = response.code();
            apiLog.success = response.isSuccessful();
            apiLog.createdAt = System.currentTimeMillis();
            apiLog.durationMs = duration;

            executor.execute(() -> apiLogDao.insertApiLogs(apiLog));

            // 🔥 RETURN FULL BODY (NOT TRUNCATED)
            return response.newBuilder()
                    .body(ResponseBody.create(fullBody, responseBody.contentType()))
                    .build();
        }
    }

    private String maskSensitive(String body) {
        if (body == null) return "";
        return body
                .replaceAll("\"password\":\".*?\"", "\"password\":\"****\"")
                .replaceAll("\"token\":\".*?\"", "\"token\":\"****\"");
    }
}