package com.hexagonal.server.ledger.application.wallet.port.out.repository;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public interface IdempotencyRepositoryPort {

    boolean exists(Id key);

    void store(Id key);

}
