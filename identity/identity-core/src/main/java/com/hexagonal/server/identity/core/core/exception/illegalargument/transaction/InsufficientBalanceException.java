package com.hexagonal.server.identity.core.core.exception.illegalargument.transaction;

import com.hexagonal.server.shared.kernel.common.exception.utils.ErrorUtils;

import static com.hexagonal.server.shared.kernel.common.exception.constants.ErrorMessageConstants.INSUFFICIENT_BALANCE_EXCEPTION;

public class InsufficientBalanceException extends IllegalArgumentException {

    public InsufficientBalanceException(final String id) {
        super(ErrorUtils.generateErrorMessage(INSUFFICIENT_BALANCE_EXCEPTION, id));
    }

}
