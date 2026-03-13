package com.hexagonal.server.identity.flow;

import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.CreateAccountRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.CreateAccountResponse;
import com.hexagonal.server.identity.common.constant.Endpoint;
import com.hexagonal.server.identity.config.BaseIdentityIntegrationTest;
import org.junit.jupiter.api.Test;

import static com.hexagonal.server.identity.common.mock.AccountCreateRequestMock.generateAccountCreateRequest;
import static org.assertj.core.api.Assertions.assertThat;

class AccountIntegrationTest extends BaseIdentityIntegrationTest {

    @Test
    void userCreatesAccount() {
        //given
        CreateAccountRequest createAccountRequest = generateAccountCreateRequest();
        //when
        CreateAccountResponse createAccountResponse = requestTestClient.post(Endpoint.CREATE_ACCOUNT, createAccountRequest)
                .expectStatus().isCreated()
                .expectBody(CreateAccountResponse.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(accountRepositoryPort.findTotalEntries()).isEqualTo(1);
        assertThat(createAccountResponse).isNotNull();
        assertThat(createAccountResponse.id()).isNotNull();
    }

}
