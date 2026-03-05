package com.hexagonal.server.identity.core.account.domain;

import com.hexagonal.server.shared.kernel.common.entity.DomainEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.*;

import java.util.Objects;

public class Account extends DomainEntity {

    private Id id;
    private Email email;
    private Username username;
    private Password password;
    private Name name;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private Account() {
    }

    public Account(
            final Email email,
            final Username username,
            final Password password,
            final Name name) {
        this.id = Id.generate();
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public Account(
            final Id id,
            final Email email,
            final Username username,
            final Password password,
            final Name name) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public Account(
            final Id id,
            final Email email,
            final Username username,
            final Password password,
            final Name name,
            final Timestamp createdAt,
            final Timestamp updatedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Id getId() {
        return id;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(email, account.email) && Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(name, account.name) && Objects.equals(createdAt, account.createdAt) && Objects.equals(updatedAt, account.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, name, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email=" + email +
                ", username=" + username +
                ", password=" + "[REDACTED]" +
                ", name=" + name +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
