package com.hexagonal.server.identity.application.account.port.in.api;

import com.hexagonal.server.identity.application.account.common.dto.AccountDto;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.CreateAccountRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.CreateAccountResponse;
import org.springframework.http.ResponseEntity;

public interface AccountApi {

    ResponseEntity<CreateAccountResponse> createAccount(CreateAccountRequest createAccountRequest);

    ResponseEntity<AccountDto> getAccount(String id);

}
