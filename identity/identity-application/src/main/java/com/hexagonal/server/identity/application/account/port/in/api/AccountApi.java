package com.hexagonal.server.identity.application.account.port.in.api;

import com.hexagonal.server.identity.application.account.common.dto.AccountDto;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.CreateAccountRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.CreateAccountResponse;
import com.hexagonal.server.shared.kernel.common.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AccountApi {

    ResponseEntity<ApiResponse<CreateAccountResponse>> createAccount(CreateAccountRequest createAccountRequest);

    ResponseEntity<ApiResponse<AccountDto>> getAccount(String id);

}
