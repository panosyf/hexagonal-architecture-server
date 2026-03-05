package com.hexagonal.server.identity.application.application.api;

import com.hexagonal.server.identity.application.application.model.request.account.AccountCreateRequest;
import com.hexagonal.server.identity.application.application.model.response.account.AccountCreationResponse;
import com.hexagonal.server.identity.application.application.model.response.account.AccountResponse;
import org.springframework.http.ResponseEntity;

public interface AccountApi {

    ResponseEntity<AccountCreationResponse> createAccount(AccountCreateRequest accountCreateRequest);

    ResponseEntity<AccountResponse> getAccount(String id);

}
