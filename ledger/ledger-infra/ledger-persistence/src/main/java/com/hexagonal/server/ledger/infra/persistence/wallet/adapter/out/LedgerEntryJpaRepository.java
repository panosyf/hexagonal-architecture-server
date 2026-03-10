package com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out;

import com.hexagonal.server.ledger.infra.persistence.wallet.entity.LedgerEntryPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerEntryJpaRepository extends JpaRepository<LedgerEntryPersistenceEntity, String> {

    List<LedgerEntryPersistenceEntity> findByWalletId(String walletId);

}

