package com.hexagonal.server.ledger.common.constant;

public class Endpoint {

    private Endpoint() {
    }

    public static final String CREATE_WALLET = "/api/v1/wallets";
    public static final String CREDIT_WALLET = "/api/v1/wallets/{id}/actions/credit";
    public static final String DEBIT_WALLET = "/api/v1/wallets/{id}/actions/debit";

}
