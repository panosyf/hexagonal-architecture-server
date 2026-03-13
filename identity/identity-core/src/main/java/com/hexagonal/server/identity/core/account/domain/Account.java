package com.hexagonal.server.identity.core.account.domain;

import com.hexagonal.server.shared.kernel.common.entity.AggregateRoot;
import com.hexagonal.server.shared.kernel.common.valueobjects.*;

import java.util.Objects;

public class Account extends AggregateRoot {

    private Email email;
    private Username username;
    private Password password;
    private Name name;

    private Account() {
    }

    private Account(
            final Email email,
            final Username username,
            final Password password,
            final Name name) {
        super(Id.generate());
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    private Account(
            final Id id,
            final Email email,
            final Username username,
            final Password password,
            final Name name,
            final Timestamp createdAt,
            final Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public static Account create(Email email, Username username, Password password, Name name) {
        return new Account(email, username, password, name);
    }

    public static Account create(
            Id id,
            Email email,
            Username username,
            Password password,
            Name name,
            Timestamp createdAt,
            Timestamp updatedAt) {
        return new Account(id, email, username, password, name, createdAt, updatedAt);
    }

    public Email getEmail() {
        return email;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(getId(), account.getId())
                && Objects.equals(email, account.email)
                && Objects.equals(username, account.username)
                && Objects.equals(password, account.password)
                && Objects.equals(name, account.name)
                && Objects.equals(getCreatedAt(), account.getCreatedAt())
                && Objects.equals(getUpdatedAt(), account.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), email, username, password, name, getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + getId() +
                ", email=" + email +
                ", username=" + username +
                ", password=[REDACTED]" +
                ", name=" + name +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }

}
