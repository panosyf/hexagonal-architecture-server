package com.hexagonal.server.identity.infra.rest.controller.transaction;

import com.hexagonal.server.identity.application.api.TransactionApi;
import com.hexagonal.server.identity.application.usecase.transaction.TransactionUsecase;
import com.hexagonal.server.identity.application.model.response.transaction.TransactionResponse;
import com.hexagonal.server.identity.application.model.request.transaction.TransactionCreateRequest;
import com.hexagonal.server.identity.application.model.request.transaction.TransactionUpdateRequest;
import com.hexagonal.server.identity.application.model.response.transaction.TransactionCreationResponse;
import com.hexagonal.server.identity.application.model.response.transaction.TransactionUpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController implements TransactionApi {

    private final TransactionUsecase transactionUsecase;

    public TransactionController(TransactionUsecase transactionUsecase) {
        this.transactionUsecase = transactionUsecase;
    }

    @Override
    @GetMapping(path = "/api/v1/transactions/{id}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(transactionUsecase.getTransaction(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/api/v1/transactions")
    public ResponseEntity<TransactionCreationResponse> createTransaction(@RequestBody TransactionCreateRequest transactionCreateRequest) {
        return new ResponseEntity<>(transactionUsecase.createTransaction(transactionCreateRequest), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/api/v1/transactions/{id}")
    public ResponseEntity<TransactionUpdateResponse> updateTransaction(
            @PathVariable(value = "id") String id,
            @RequestBody TransactionUpdateRequest transactionUpdateRequest) {
        return new ResponseEntity<>(transactionUsecase.updateTransaction(id, transactionUpdateRequest), HttpStatus.OK);
    }

}
