package com.hexagonal.server.ledger.application.wallet.port.out.repository;

public interface IdempotencyRepository {

    boolean exists(String key);

    void store(String key);

}
