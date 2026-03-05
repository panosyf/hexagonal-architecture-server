package com.hexagonal.server.identity.application.account.usecase;

import com.hexagonal.server.identity.application.account.model.dto.AccountDto;
import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.application.account.model.response.AccountResponse;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.enums.AccountCreationStatusEnum;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.model.operation.GetAccountOperation;
import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.springframework.core.convert.ConversionService;

public class AccountUsecaseImpl implements AccountUsecase {

    private final AccountDomainService accountDomainService;
    private final ConversionService conversionService;

    public AccountUsecaseImpl(AccountDomainService accountDomainService, ConversionService conversionService) {
        this.accountDomainService = accountDomainService;
        this.conversionService = conversionService;
    }

    @Override
    public AccountCreationResponse createAccount(AccountCreateRequest accountCreateRequest) {
        CreateAccountOperation createAccountOperation = conversionService.convert(accountCreateRequest, CreateAccountOperation.class);
        Account account = accountDomainService.createAccount(createAccountOperation);
        return new AccountCreationResponse(account.getId().getValue(), AccountCreationStatusEnum.SUCCESSFUL);
    }

    @Override
    public AccountResponse getAccount(String id) {
        GetAccountOperation getAccountOperation = new GetAccountOperation(Id.valueOf(id));
        Account account = accountDomainService.getAccount(getAccountOperation);
        AccountDto accountDto = conversionService.convert(account, AccountDto.class);
        return new AccountResponse(accountDto);
    }

}
