package com.hexagonal.server.ledger.application.wallet.port.out.repository;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

import java.util.List;

public interface LedgerEntryRepositoryPort {

    void save(LedgerEntry ledgerEntry);

    List<LedgerEntry> findByWalletId(Id walletId);

    int findTotalEntries();

    void deleteAll();

}
