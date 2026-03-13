package com.hexagonal.server.identity.application.account.usecase.createaccount.model.request;

public record CreateAccountRequest(
        String email,
        String username,
        String password,
        String firstname,
        String lastname) {
}
