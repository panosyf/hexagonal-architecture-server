package com.hexagonal.server.identity.application.account.usecase.createaccount.mapper;

import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.core.account.model.CreateAccountOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Email;
import com.hexagonal.server.shared.kernel.common.valueobjects.Name;
import com.hexagonal.server.shared.kernel.common.valueobjects.Password;
import com.hexagonal.server.shared.kernel.common.valueobjects.Username;

public class CreateAccountMapper {

    private CreateAccountMapper() {
    }

    public static CreateAccountOperation toCreateAccountOperation(AccountCreateRequest accountCreateRequest) {
        Email email = Email.valueOf(accountCreateRequest.email());
        Username username = Username.valueOf(accountCreateRequest.username());
        // TODO UTILIZE HASHING AND SALT
        // TODO HIDE PASSWORD FROM LOGS
        Password password = Password.valueOf(accountCreateRequest.password());
        Name name = Name.valueOf(accountCreateRequest.firstname(), accountCreateRequest.lastname());
        return new CreateAccountOperation(email, username, password, name);
    }

}
