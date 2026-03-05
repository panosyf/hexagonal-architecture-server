package com.hexagonal.server.account.core.exception.elementnotfound.transaction;

import com.hexagonal.server.account.core.exception.utils.message.transaction.ErrorMessageConstant;
import com.hexagonal.server.shared.kernel.common.exception.types.ElementNotFoundException;
import com.hexagonal.server.shared.kernel.common.exception.utils.ErrorUtils;

public class TransactionNotFoundException extends ElementNotFoundException {

    public TransactionNotFoundException(final String id) {
        super(ErrorUtils.generateErrorMessage(ErrorMessageConstant.TRANSACTION_NOT_FOUND_EXCEPTION, id));
    }
}
