package com.hexagonal.server.infra.persistence.converter.transaction;

import com.hexagonal.server.identity.core.core.domain.transaction.Transaction;
import com.hexagonal.server.infra.persistence.entity.transaction.TransactionPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.springframework.core.convert.converter.Converter;

public class TransactionToDomain implements Converter<TransactionPersistenceEntity, Transaction> {

    @Override
    public Transaction convert(TransactionPersistenceEntity transactionPersistenceEntity) {
        return new Transaction(
                Id.valueOf(transactionPersistenceEntity.getId()),
                transactionPersistenceEntity.getType(),
                transactionPersistenceEntity.getAmount(),
                transactionPersistenceEntity.getDescription(),
                Id.valueOf(transactionPersistenceEntity.getDebtorAccountId()),
                Id.valueOf(transactionPersistenceEntity.getBeneficiaryAccountId()),
                transactionPersistenceEntity.getStatus(),
                transactionPersistenceEntity.getCreatedAt(),
                transactionPersistenceEntity.getUpdatedAt());
    }

}
