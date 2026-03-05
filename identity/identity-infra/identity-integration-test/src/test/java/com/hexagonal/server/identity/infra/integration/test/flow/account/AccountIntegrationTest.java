package com.hexagonal.server.identity.infra.integration.test.flow.account;

import com.hexagonal.server.identity.infra.integration.test.common.constant.account.Endpoint;
import com.hexagonal.server.identity.core.model.enums.account.AccountCreationStatusEnum;
import com.hexagonal.server.identity.application.model.request.account.AccountCreateRequest;
import com.hexagonal.server.identity.application.model.response.account.AccountCreationResponse;
import com.hexagonal.server.identity.infra.integration.test.config.AppIntegrationTest;
import org.junit.jupiter.api.Test;

import static com.hexagonal.server.identity.infra.integration.test.common.mock.account.AccountCreateRequestMock.generateAccountCreateRequest;
import static org.assertj.core.api.Assertions.assertThat;

class AccountIntegrationTest extends AppIntegrationTest {

    @Test
    void userCreatesAccount() {
        //given
        AccountCreateRequest accountCreateRequest = generateAccountCreateRequest();
        //when
        AccountCreationResponse accountCreationResponse = requestTestClient.post(Endpoint.CREATE_ACCOUNT, accountCreateRequest)
                .expectStatus().isCreated()
                .expectBody(AccountCreationResponse.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(accountRepositoryPort.findTotalEntries()).isEqualTo(1);
        assertThat(accountCreationResponse).isNotNull();
        assertThat(accountCreationResponse.status()).isEqualTo(AccountCreationStatusEnum.SUCCESSFUL);
    }

}
