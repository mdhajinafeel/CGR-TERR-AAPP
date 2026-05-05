package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "api_logs",
        indices = {
                @Index(name = "idx_endpoint", value = {"endpoint"}),
                @Index(name = "idx_method", value = {"method"}),
                @Index(name = "idx_created_at", value = {"createdAt"})
        })
public class ApiLogs implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String endpoint;
    public String method;
    public String requestBody;
    public String responseBody;
    public int statusCode;
    public boolean success;
    public String errorMessage;
    public long createdAt;
    public long durationMs;
    public boolean isSlow;
    public boolean isLarge;
    public String type;
    public String exceptionType;
    public String tag;
    public String methodName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public boolean isSlow() {
        return isSlow;
    }

    public void setSlow(boolean slow) {
        isSlow = slow;
    }

    public boolean isLarge() {
        return isLarge;
    }

    public void setLarge(boolean large) {
        isLarge = large;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}