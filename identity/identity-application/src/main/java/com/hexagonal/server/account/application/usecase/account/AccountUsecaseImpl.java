package com.hexagonal.server.account.application.usecase.account;

import com.hexagonal.server.account.application.model.dto.account.AccountDto;
import com.hexagonal.server.account.application.model.request.account.AccountCreateRequest;
import com.hexagonal.server.account.application.model.response.account.AccountCreationResponse;
import com.hexagonal.server.account.application.model.response.account.AccountResponse;
import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.account.core.model.enums.account.AccountCreationStatusEnum;
import com.hexagonal.server.account.core.service.account.AccountDomainService;
import com.hexagonal.server.account.core.model.operation.account.CreateAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.DecreaseBalanceOperation;
import com.hexagonal.server.account.core.model.operation.account.GetAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.IncreaseBalanceOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.springframework.core.convert.ConversionService;

import java.math.BigDecimal;

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

    @Override
    public void increaseBalance(String id, BigDecimal amount) {
        IncreaseBalanceOperation increaseBalanceOperation = new IncreaseBalanceOperation(Id.valueOf(id), Money.of(amount));
        accountDomainService.increaseBalance(increaseBalanceOperation);
    }

    @Override
    public void decreaseBalance(String id, BigDecimal amount) {
        DecreaseBalanceOperation decreaseBalanceOperation = new DecreaseBalanceOperation(Id.valueOf(id), Money.of(amount));
        accountDomainService.decreaseBalance(decreaseBalanceOperation);
    }

}
