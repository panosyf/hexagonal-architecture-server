package com.hexagonal.server.identity.application.account.port.in.api;

import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.application.account.model.response.AccountResponse;
import org.springframework.http.ResponseEntity;

public interface AccountApi {

    ResponseEntity<AccountCreationResponse> createAccount(AccountCreateRequest accountCreateRequest);

    ResponseEntity<AccountResponse> getAccount(String id);

}
