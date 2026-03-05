package com.hexagonal.server.account.core.exception.utils.message.transaction;


public class ErrorMessageConstant {

    private ErrorMessageConstant() {
    }

    public static final String TRANSACTION_NOT_FOUND_EXCEPTION = "Transaction with id: %s not found";
    public static final String INSUFFICIENT_BALANCE_EXCEPTION = "Account with id: %s has insufficient balance";

}
