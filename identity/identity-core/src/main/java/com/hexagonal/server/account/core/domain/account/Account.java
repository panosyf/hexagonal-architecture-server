package com.hexagonal.server.account.core.domain.account;

import com.hexagonal.server.account.core.exception.illegalargument.transaction.InsufficientBalanceException;
import com.hexagonal.server.shared.kernel.common.entity.DomainEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.*;

import java.util.Objects;

public class Account extends DomainEntity {

    private Id id;
    private Email email;
    private Username username;
    private Password password;
    private Name name;
    private Money balance;
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
        this.balance = Money.zero();
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public Account(
            final Email email,
            final Username username,
            final Password password,
            final Name name,
            final Money balance) {
        this.id = Id.generate();
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = balance;
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
            final Money balance) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = balance;
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
            final Money balance,
            final Timestamp createdAt,
            final Timestamp updatedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = balance;
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

    public Money getBalance() {
        return balance;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public boolean hasBalance() {
        return this.balance.isGreaterThanZero();
    }

    public boolean isBalanceEligibleForTransaction(final Money transactionAmount) {
        return hasBalance() && balance.isGreaterThanOrEqual(transactionAmount);
    }

    public boolean notEligibleBalanceForTransaction(final Money transactionAmount) {
        return !isBalanceEligibleForTransaction(transactionAmount);
    }

    public void validateBalanceEligibleForTransaction(final Money amount) {
        if (notEligibleBalanceForTransaction(amount)) {
            throw new InsufficientBalanceException(id.getValue());
        }
    }

    public void increaseBalance(final Money amount) {
        this.balance = this.balance.add(amount);
    }

    public void decreaseBalance(final Money amount) {
        validateBalanceEligibleForTransaction(amount);
        this.balance = this.balance.subtract(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(email, account.email) && Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(name, account.name) && Objects.equals(balance, account.balance) && Objects.equals(createdAt, account.createdAt) && Objects.equals(updatedAt, account.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, name, balance, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email=" + email +
                ", username=" + username +
                ", password=" + "[REDACTED]" +
                ", name=" + name +
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
