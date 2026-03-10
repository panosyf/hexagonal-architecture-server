package com.hexagonal.server.identity.application.account.common.exception;

import com.hexagonal.server.shared.kernel.common.exception.ElementNotFoundException;
import com.hexagonal.server.shared.kernel.common.exception.utils.ErrorUtils;

import static com.hexagonal.server.identity.application.account.common.exception.AccountErrorMessageConstant.ACCOUNT_NOT_FOUND_EXCEPTION;

public class AccountNotFoundException extends ElementNotFoundException {

    public AccountNotFoundException(final String id) {
        super(ErrorUtils.generateErrorMessage(ACCOUNT_NOT_FOUND_EXCEPTION, id));
    }

}
