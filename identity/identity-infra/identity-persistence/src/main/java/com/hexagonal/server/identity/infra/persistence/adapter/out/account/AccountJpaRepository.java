package com.hexagonal.server.identity.infra.persistence.adapter.out.account;

import com.hexagonal.server.identity.infra.persistence.entity.account.AccountPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface AccountJpaRepository extends JpaRepository<AccountPersistenceEntity, String> {

    @Query(
            value = "SELECT balance " +
                    "FROM account " +
                    "WHERE id = :id",
            nativeQuery = true
    )
    BigDecimal findBalance(@Param("id") String id);

    @Modifying
    @Query(
            value = "UPDATE account " +
                    "SET balance = :amount " +
                    "WHERE id = :id",
            nativeQuery = true)
    void updateBalance(@Param("id") String id, @Param("amount") BigDecimal amount);

    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM account",
            nativeQuery = true
    )
    int findTotalEntries();

}
