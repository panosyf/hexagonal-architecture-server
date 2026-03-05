package com.hexagonal.server.infra.persistence.adapter.out.transaction;

import com.hexagonal.server.identity.core.domain.transaction.Transaction;
import com.hexagonal.server.identity.core.exception.elementnotfound.transaction.TransactionNotFoundException;
import com.hexagonal.server.identity.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.identity.core.port.out.transaction.TransactionRepositoryPort;
import com.hexagonal.server.infra.persistence.entity.transaction.TransactionPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.transaction.Transactional;
import org.springframework.core.convert.ConversionService;

public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final TransactionJpaRepository transactionJpaRepository;
    private final ConversionService conversionService;

    public TransactionRepositoryAdapter(
            TransactionJpaRepository transactionJpaRepository,
            ConversionService conversionService) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.conversionService = conversionService;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionPersistenceEntity transactionPersistenceEntity = conversionService.convert(transaction, TransactionPersistenceEntity.class);
        TransactionPersistenceEntity persistedTransactionPersistenceEntity = transactionJpaRepository.save(transactionPersistenceEntity);
        return transactionToDomain(persistedTransactionPersistenceEntity);
    }

    @Override
    public Transaction findById(Id id) {
        TransactionPersistenceEntity transactionPersistenceEntity = transactionJpaRepository.findById(id.getValue())
                .orElseThrow(() -> new TransactionNotFoundException(id.getValue()));
        return transactionToDomain(transactionPersistenceEntity);
    }

    @Override
    @Transactional
    public Transaction updateStatus(Transaction transaction) {
        Id id = transaction.getId();
        TransactionStatusEnum status = transaction.getStatus();
        Timestamp updatedAt = transaction.getUpdatedAt();
        transactionJpaRepository.updateStatus(id.getValue(), status, updatedAt.getTime());
        return findById(id);
    }

    @Override
    public void deleteAll() {
        transactionJpaRepository.deleteAll();
    }

    private Transaction transactionToDomain(TransactionPersistenceEntity transactionPersistenceEntity) {
        return conversionService.convert(transactionPersistenceEntity, Transaction.class);
    }

}
