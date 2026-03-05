package com.hexagonal.server.infra.rest.controller.account;

import com.hexagonal.server.identity.application.api.AccountApi;
import com.hexagonal.server.identity.application.usecase.account.AccountUsecase;
import com.hexagonal.server.identity.application.model.response.account.AccountCreationResponse;
import com.hexagonal.server.identity.application.model.response.account.AccountResponse;
import com.hexagonal.server.identity.application.model.request.account.AccountCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController implements AccountApi {

    private final AccountUsecase accountUsecase;

    public AccountController(AccountUsecase accountUsecase) {
        this.accountUsecase = accountUsecase;
    }

    @Override
    @PostMapping(path = "/api/v1/accounts")
    public ResponseEntity<AccountCreationResponse> createAccount(@RequestBody AccountCreateRequest accountCreateRequest) {
        return new ResponseEntity<>(accountUsecase.createAccount(accountCreateRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/api/v1/accounts/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(accountUsecase.getAccount(id), HttpStatus.OK);
    }

}
