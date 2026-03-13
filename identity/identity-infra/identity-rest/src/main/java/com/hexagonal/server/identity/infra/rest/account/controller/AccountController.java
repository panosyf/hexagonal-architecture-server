package com.hexagonal.server.identity.infra.rest.account.controller;

import com.hexagonal.server.identity.application.account.common.dto.AccountDto;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.CreateAccountRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.CreateAccountResponse;
import com.hexagonal.server.identity.application.account.port.in.api.AccountApi;
import com.hexagonal.server.identity.application.account.usecase.createaccount.CreateAccountUsecase;
import com.hexagonal.server.identity.application.account.usecase.getaccount.GetAccountUsecase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController implements AccountApi {

    private final CreateAccountUsecase createAccountUsecase;
    private final GetAccountUsecase getAccountUsecase;

    public AccountController(
            @Qualifier("createAccountUsecase") CreateAccountUsecase createAccountUsecase,
            @Qualifier("getAccountUsecase") GetAccountUsecase getAccountUsecase) {
        this.createAccountUsecase = createAccountUsecase;
        this.getAccountUsecase = getAccountUsecase;
    }

    @Override
    @PostMapping(path = "/api/v1/accounts")
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return new ResponseEntity<>(createAccountUsecase.createAccount(createAccountRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/api/v1/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(getAccountUsecase.getAccount(id), HttpStatus.OK);
    }

}
