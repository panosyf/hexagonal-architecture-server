package com.hexagonal.server.ledger.application.wallet.port.out.repository;

public interface IdempotencyRepositoryPort {

    boolean exists(String key);

    void store(String key);

}
