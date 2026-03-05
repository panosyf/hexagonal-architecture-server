package com.hexagonal.server.account.infra.integration.test.common.constant.transaction;

public class Endpoint {

    private Endpoint() {
    }

    public static final String CREATE_TRANSACTION = "/api/v1/transactions";
    public static final String UPDATE_TRANSACTION = "/api/v1/transactions/{id}";

}
