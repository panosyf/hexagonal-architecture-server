package com.hexagonal.server.identity.application.application.api;

import com.hexagonal.server.identity.application.application.model.request.transaction.TransactionCreateRequest;
import com.hexagonal.server.identity.application.application.model.request.transaction.TransactionUpdateRequest;
import com.hexagonal.server.identity.application.application.model.response.transaction.TransactionCreationResponse;
import com.hexagonal.server.identity.application.application.model.response.transaction.TransactionResponse;
import com.hexagonal.server.identity.application.application.model.response.transaction.TransactionUpdateResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionApi {

    ResponseEntity<TransactionResponse> getTransaction(String id);

    ResponseEntity<TransactionCreationResponse> createTransaction(TransactionCreateRequest transactionCreateRequest);

    ResponseEntity<TransactionUpdateResponse> updateTransaction(String id, TransactionUpdateRequest transactionUpdateRequest);

}
