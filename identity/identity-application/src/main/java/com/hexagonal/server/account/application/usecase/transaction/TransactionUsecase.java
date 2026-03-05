package com.hexagonal.server.account.application.usecase.transaction;

import com.hexagonal.server.account.application.model.request.transaction.TransactionCreateRequest;
import com.hexagonal.server.account.application.model.request.transaction.TransactionUpdateRequest;
import com.hexagonal.server.account.application.model.response.transaction.TransactionCreationResponse;
import com.hexagonal.server.account.application.model.response.transaction.TransactionResponse;
import com.hexagonal.server.account.application.model.response.transaction.TransactionUpdateResponse;

public interface TransactionUsecase {

    TransactionResponse getTransaction(String id);

    TransactionCreationResponse createTransaction(TransactionCreateRequest transactionCreateRequest);

    TransactionUpdateResponse updateTransaction(String id, TransactionUpdateRequest transactionUpdateRequest);

}
