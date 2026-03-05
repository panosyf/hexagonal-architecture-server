package com.hexagonal.server.infra.persistence.converter.transaction;

import com.hexagonal.server.identity.core.domain.transaction.Transaction;
import com.hexagonal.server.infra.persistence.entity.transaction.TransactionPersistenceEntity;
import org.springframework.core.convert.converter.Converter;

public class TransactionToEntity implements Converter<Transaction, TransactionPersistenceEntity> {

    @Override
    public TransactionPersistenceEntity convert(Transaction transaction) {
        return new TransactionPersistenceEntity(
                transaction.getId().getValue(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDebtorAccountId().getValue(),
                transaction.getBeneficiaryAccountId().getValue(),
                transaction.getStatus(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }

}
