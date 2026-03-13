package com.hexagonal.server.shared.kernel.common.model;

import java.time.Instant;

public class ApiResponse<T> {

    private T data;
    private Instant timestamp;

    public ApiResponse() {
    }

    public ApiResponse(T data) {
        this.data = data;
        this.timestamp = Instant.now();
    }

    public T getData() {
        return data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

