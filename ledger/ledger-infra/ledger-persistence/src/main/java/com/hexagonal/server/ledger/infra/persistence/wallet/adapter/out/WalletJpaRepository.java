package com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out;

import com.hexagonal.server.ledger.infra.persistence.wallet.entity.WalletPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletJpaRepository extends JpaRepository<WalletPersistenceEntity, String> {

    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM wallet",
            nativeQuery = true
    )
    int findTotalEntries();

}
