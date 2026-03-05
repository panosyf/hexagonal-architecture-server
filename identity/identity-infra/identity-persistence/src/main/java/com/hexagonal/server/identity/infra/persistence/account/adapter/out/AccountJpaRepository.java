package com.hexagonal.server.identity.infra.persistence.account.adapter.out;

import com.hexagonal.server.identity.infra.persistence.account.entity.AccountPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountJpaRepository extends JpaRepository<AccountPersistenceEntity, String> {

    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM account",
            nativeQuery = true
    )
    int findTotalEntries();

}
