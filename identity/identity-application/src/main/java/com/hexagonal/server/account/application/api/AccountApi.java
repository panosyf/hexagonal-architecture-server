package com.hexagonal.server.account.application.api;

import com.hexagonal.server.account.application.model.request.account.AccountCreateRequest;
import com.hexagonal.server.account.application.model.response.account.AccountCreationResponse;
import com.hexagonal.server.account.application.model.response.account.AccountResponse;
import org.springframework.http.ResponseEntity;

public interface AccountApi {

    ResponseEntity<AccountCreationResponse> createAccount(AccountCreateRequest accountCreateRequest);

    ResponseEntity<AccountResponse> getAccount(String id);

}
