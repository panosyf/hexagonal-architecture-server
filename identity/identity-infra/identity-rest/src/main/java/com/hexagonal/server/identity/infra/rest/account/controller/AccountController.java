package com.hexagonal.server.identity.infra.rest.account.controller;

import com.hexagonal.server.identity.application.account.model.dto.AccountDto;
import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.application.account.port.in.api.AccountApi;
import com.hexagonal.server.identity.application.account.usecase.createaccount.CreateAccountUsecase;
import com.hexagonal.server.identity.application.account.usecase.getaccount.GetAccountUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController implements AccountApi {

    private final CreateAccountUsecase createAccountUsecase;
    private final GetAccountUsecase getAccountUsecase;

    public AccountController(CreateAccountUsecase createAccountUsecase, GetAccountUsecase getAccountUsecase) {
        this.createAccountUsecase = createAccountUsecase;
        this.getAccountUsecase = getAccountUsecase;
    }

    @Override
    @PostMapping(path = "/api/v1/accounts")
    public ResponseEntity<AccountCreationResponse> createAccount(@RequestBody AccountCreateRequest accountCreateRequest) {
        return new ResponseEntity<>(createAccountUsecase.createAccount(accountCreateRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/api/v1/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(getAccountUsecase.getAccount(id), HttpStatus.OK);
    }

}
