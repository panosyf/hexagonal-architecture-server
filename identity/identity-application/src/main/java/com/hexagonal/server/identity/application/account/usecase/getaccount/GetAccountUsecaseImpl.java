package com.hexagonal.server.identity.application.account.usecase.getaccount;

import com.hexagonal.server.identity.application.account.shared.dto.AccountDto;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.springframework.core.convert.ConversionService;

public class GetAccountUsecaseImpl implements GetAccountUsecase {

    private final AccountRepositoryPort accountRepositoryPort;
    private final ConversionService conversionService;

    public GetAccountUsecaseImpl(
            AccountRepositoryPort accountRepositoryPort,
            ConversionService conversionService) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.conversionService = conversionService;
    }

    @Override
    public AccountDto getAccount(String id) {
        Account account = accountRepositoryPort.findById(Id.valueOf(id));
        return conversionService.convert(account, AccountDto.class);
    }

}
