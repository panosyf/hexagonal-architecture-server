package com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out;

import com.hexagonal.server.ledger.infra.persistence.wallet.entity.LedgerEntryPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LedgerEntryJpaRepository extends JpaRepository<LedgerEntryPersistenceEntity, String> {

    List<LedgerEntryPersistenceEntity> findByWalletId(String walletId);

    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM ledger_entry",
            nativeQuery = true
    )
    int findTotalEntries();

}

