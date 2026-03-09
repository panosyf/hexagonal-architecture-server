package com.hexagonal.server.identity.application.account.mapper;

import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Email;
import com.hexagonal.server.shared.kernel.common.valueobjects.Name;
import com.hexagonal.server.shared.kernel.common.valueobjects.Password;
import com.hexagonal.server.shared.kernel.common.valueobjects.Username;
import org.springframework.core.convert.converter.Converter;

public class AccountCreateRequestToOperation implements Converter<AccountCreateRequest, CreateAccountOperation> {

    @Override
    public CreateAccountOperation convert(AccountCreateRequest accountCreateRequest) {
        Email email = Email.valueOf(accountCreateRequest.email());
        Username username = Username.valueOf(accountCreateRequest.username());
        // TODO UTILIZE HASHING AND SALT
        // TODO HIDE PASSWORD FROM LOGS
        Password password = Password.valueOf(accountCreateRequest.password());
        Name name = Name.valueOf(accountCreateRequest.firstname(), accountCreateRequest.lastname());
        return new CreateAccountOperation(email, username, password, name);
    }

}
