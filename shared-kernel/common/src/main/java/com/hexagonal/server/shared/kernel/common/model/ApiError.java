package com.hexagonal.server.shared.kernel.common.model;

import java.time.Instant;

public class ApiError {

    private final String code;
    private final String message;
    private final Instant timestamp;

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}

