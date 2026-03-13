package com.hexagonal.server.identity.flow;

import com.hexagonal.server.identity.application.account.common.dto.AccountDto;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.CreateAccountRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.CreateAccountResponse;
import com.hexagonal.server.identity.common.constant.Endpoint;
import com.hexagonal.server.identity.config.BaseIdentityIntegrationTest;
import com.hexagonal.server.shared.kernel.common.model.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;

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
                .expectBody(new ParameterizedTypeReference<ApiResponse<CreateAccountResponse>>() {
                })
                .returnResult()
                .getResponseBody()
                .getData();
        //then
        assertThat(accountRepositoryPort.findTotalEntries()).isEqualTo(1);
        assertThat(createAccountResponse).isNotNull();
        String createdAccountId = createAccountResponse.id();
        assertThat(createdAccountId).isNotNull();
        AccountDto accountDto = requestTestClient.get(
                        Endpoint.GET_ACCOUNT.replace("{id}", createdAccountId))
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse<AccountDto>>() {
                })
                .returnResult()
                .getResponseBody()
                .getData();
        assertThat(accountDto).isNotNull();
        assertThat(accountDto.id()).isEqualTo(createdAccountId);
    }

}
