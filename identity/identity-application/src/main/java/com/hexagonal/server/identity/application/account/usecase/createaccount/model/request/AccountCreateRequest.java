package com.hexagonal.server.identity.application.account.usecase.createaccount.model.request;

public record AccountCreateRequest(
        String email,
        String username,
        String password,
        String firstname,
        String lastname) {
}
