package com.hexagonal.server.ledger.application.wallet.port.out.repository;

import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public interface WalletRepositoryPort {

    Wallet save(Wallet wallet);

    Wallet findById(Id walletId);

    int findTotalEntries();

    void deleteAll();

}
