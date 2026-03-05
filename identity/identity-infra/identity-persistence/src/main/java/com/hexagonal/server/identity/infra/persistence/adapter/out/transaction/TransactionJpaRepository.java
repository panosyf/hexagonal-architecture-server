package com.hexagonal.server.identity.infra.persistence.adapter.out.transaction;

import com.hexagonal.server.identity.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.identity.infra.persistence.entity.transaction.TransactionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface TransactionJpaRepository extends JpaRepository<TransactionPersistenceEntity, String> {

    @Modifying
    @Query(
            value = "UPDATE transaction " +
                    "SET status = :status, " +
                    "updated_at = :updatedAt " +
                    "WHERE id = :id",
            nativeQuery = true)
    void updateStatus(
            @Param("id") String id,
            @Param("status") TransactionStatusEnum status,
            @Param("updatedAt") Instant updatedAt);

}
