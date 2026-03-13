package com.hexagonal.server.identity.application.account.usecase.createaccount.mapper;

import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.CreateAccountRequest;
import com.hexagonal.server.identity.core.account.model.CreateAccountOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Email;
import com.hexagonal.server.shared.kernel.common.valueobjects.Name;
import com.hexagonal.server.shared.kernel.common.valueobjects.Password;
import com.hexagonal.server.shared.kernel.common.valueobjects.Username;

public class CreateAccountMapper {

    private CreateAccountMapper() {
    }

    public static CreateAccountOperation toCreateAccountOperation(CreateAccountRequest createAccountRequest) {
        Email email = Email.valueOf(createAccountRequest.email());
        Username username = Username.valueOf(createAccountRequest.username());
        // TODO UTILIZE HASHING AND SALT
        // TODO HIDE PASSWORD FROM LOGS
        Password password = Password.valueOf(createAccountRequest.password());
        Name name = Name.valueOf(createAccountRequest.firstname(), createAccountRequest.lastname());
        return new CreateAccountOperation(email, username, password, name);
    }

}
