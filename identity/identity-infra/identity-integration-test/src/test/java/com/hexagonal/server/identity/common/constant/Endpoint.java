package com.hexagonal.server.identity.common.constant;

public class Endpoint {

    private Endpoint() {
    }

    public static final String CREATE_ACCOUNT = "/api/v1/accounts";
    public static final String GET_ACCOUNT = "/api/v1/accounts/{id}";

}
