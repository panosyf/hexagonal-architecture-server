package com.hexagonal.server.identity.application.account.usecase.getaccount;

import com.hexagonal.server.identity.application.account.common.constant.AccountId;
import com.hexagonal.server.identity.application.account.common.constant.Name;
import com.hexagonal.server.identity.application.account.common.dto.AccountDto;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static com.hexagonal.server.identity.application.account.common.mock.AccountMock.generateAccount;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class GetAccountUsecaseImplTest {

    private final AccountRepositoryPort accountRepositoryPort = mock(AccountRepositoryPort.class);
    private GetAccountUsecase getAccountUsecase;
    private final ArgumentCaptor<Id> idCaptor = ArgumentCaptor.forClass(Id.class);

    @BeforeEach
    void init() {
        getAccountUsecase = new GetAccountUsecaseImpl(accountRepositoryPort);
    }

    @Test
    void getAccountTest() {
        // given
        Id accountId1 = AccountId.ACCOUNT_ID_1;
        Account account = generateAccount();
        given(accountRepositoryPort.findById(any(Id.class)))
                .willReturn(account);
        // when
        AccountDto accountDto = getAccountUsecase.getAccount(accountId1.getValue());
        // then
        verify(accountRepositoryPort, times(1))
                .findById(idCaptor.capture());
        Id idCaptorValue = idCaptor.getValue();
        assertAll(
                () -> assertEquals(accountId1, idCaptorValue),
                () -> assertEquals(Name.ACCOUNT_NAME_1.getFirstName(), accountDto.firstname()),
                () -> assertEquals(Name.ACCOUNT_NAME_1.getLastName(), accountDto.lastname()),
                () -> assertEquals(account.getCreatedAt().getTime(), accountDto.createdAt()),
                () -> assertEquals(account.getUpdatedAt().getTime(), accountDto.updatedAt())
        );
    }

}
