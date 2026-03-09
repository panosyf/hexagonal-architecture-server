package com.hexagonal.server.identity.application.account.port.in.api;

import com.hexagonal.server.identity.application.account.shared.dto.AccountDto;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.AccountCreationResponse;
import org.springframework.http.ResponseEntity;

public interface AccountApi {

    ResponseEntity<AccountCreationResponse> createAccount(AccountCreateRequest accountCreateRequest);

    ResponseEntity<AccountDto> getAccount(String id);

}
