package com.hexagonal.server.identity.core.core.service.transaction;

import com.hexagonal.server.identity.core.core.domain.transaction.Transaction;
import com.hexagonal.server.identity.core.core.model.operation.transaction.CreateTransactionOperation;
import com.hexagonal.server.identity.core.core.model.operation.transaction.GetTransactionOperation;
import com.hexagonal.server.identity.core.core.model.operation.transaction.UpdateTransactionOperation;

public interface TransactionDomainService {

    Transaction getTransaction(GetTransactionOperation getTransactionOperation);

    Transaction createTransaction(CreateTransactionOperation createTransactionOperation);

    Transaction updateTransaction(UpdateTransactionOperation updateTransactionOperation);

}
