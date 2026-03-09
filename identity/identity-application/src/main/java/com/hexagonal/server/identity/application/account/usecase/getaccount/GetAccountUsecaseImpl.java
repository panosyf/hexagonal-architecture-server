package com.hexagonal.server.identity.application.account.usecase.getaccount;

import com.hexagonal.server.identity.application.account.common.dto.AccountDto;
import com.hexagonal.server.identity.application.account.common.mapper.AccountMapper;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public class GetAccountUsecaseImpl implements GetAccountUsecase {

    private final AccountRepositoryPort accountRepositoryPort;

    public GetAccountUsecaseImpl(
            AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public AccountDto getAccount(String id) {
        Account account = accountRepositoryPort.findById(Id.valueOf(id));
        return AccountMapper.toAccountDto(account);
    }

}
