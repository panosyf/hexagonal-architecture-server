package com.hexagonal.server.account.core.service.transaction;

import com.hexagonal.server.account.core.domain.transaction.Transaction;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.model.operation.transaction.CreateTransactionOperation;
import com.hexagonal.server.account.core.model.operation.transaction.GetTransactionOperation;
import com.hexagonal.server.account.core.model.operation.transaction.UpdateTransactionOperation;
import com.hexagonal.server.account.core.port.out.transaction.TransactionRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionDomainServiceImpl implements TransactionDomainService {

    private final TransactionRepositoryPort transactionRepositoryPort;

    private final Logger log = LoggerFactory.getLogger(TransactionDomainServiceImpl.class);

    public TransactionDomainServiceImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public Transaction getTransaction(GetTransactionOperation getTransactionOperation) {
        log.info("TransactionDomainServiceImpl");
        return transactionRepositoryPort.findById(getTransactionOperation.id());
    }

    @Override
    public Transaction createTransaction(CreateTransactionOperation createTransactionOperation) {
        Transaction transaction = new Transaction(
                createTransactionOperation.type(),
                createTransactionOperation.amount(),
                createTransactionOperation.description(),
                createTransactionOperation.debtorAccountId(),
                createTransactionOperation.beneficiaryAccountId(),
                TransactionStatusEnum.PENDING
        );
        return transactionRepositoryPort.save(transaction);
    }

    @Override
    public Transaction updateTransaction(UpdateTransactionOperation updateTransactionOperation) {
        Transaction transaction = transactionRepositoryPort.findById(updateTransactionOperation.id());
        transaction.updateStatus(updateTransactionOperation.transactionStatusEnum());
        return transactionRepositoryPort.updateStatus(transaction);
    }

}
