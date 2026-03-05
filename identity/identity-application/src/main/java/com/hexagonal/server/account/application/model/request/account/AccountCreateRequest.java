package com.hexagonal.server.account.application.model.request.account;

public record AccountCreateRequest(
        String email,
        String username,
        String password,
        String firstname,
        String lastname) {
}
