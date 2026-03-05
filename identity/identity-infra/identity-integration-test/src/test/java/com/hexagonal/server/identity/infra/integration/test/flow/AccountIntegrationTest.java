package com.hexagonal.server.identity.infra.integration.test.flow;

import com.hexagonal.server.identity.infra.integration.test.common.constant.Endpoint;
import com.hexagonal.server.identity.core.account.model.enums.AccountCreationStatusEnum;
import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.infra.integration.test.config.BaseIdentityIntegrationTest;
import org.junit.jupiter.api.Test;

import static com.hexagonal.server.identity.infra.integration.test.common.mock.AccountCreateRequestMock.generateAccountCreateRequest;
import static org.assertj.core.api.Assertions.assertThat;

class AccountIntegrationTest extends BaseIdentityIntegrationTest {

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
