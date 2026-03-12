package com.hexagonal.server.ledger.core.wallet.domain;

import com.hexagonal.server.shared.kernel.common.entity.AggregateRoot;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;

import java.util.Objects;

import static com.hexagonal.server.ledger.core.wallet.exception.WalletErrorMessageConstants.INSUFFICIENT_FUNDS;

public class Wallet extends AggregateRoot {

    private Id id;
    private Id accountId;
    private Money balance;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private Wallet() {
    }

    private Wallet(final Id id, final Id accountId) {
        this.id = id;
        this.accountId = accountId;
        this.balance = Money.zero();
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    private Wallet(final Id id, final Id accountId, final Money balance, final Timestamp createdAt, final Timestamp updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Wallet create(final Id accountId) {
        return new Wallet(Id.generate(), accountId);
    }

    public static Wallet create(final Id id, final Id accountId, final Timestamp createdAt, final Timestamp updatedAt) {
        return new Wallet(id, accountId, Money.zero(), createdAt, updatedAt);
    }

    public static Wallet create(final Id id, final Id accountId, final Money balance, final Timestamp createdAt, final Timestamp updatedAt) {
        return new Wallet(id, accountId, balance, createdAt, updatedAt);
    }

    public Id getId() {
        return id;
    }

    public Id getAccountId() {
        return accountId;
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

    public LedgerEntry credit(Money amount, Description reference) {
        this.balance = this.balance.add(amount);
        this.updatedAt = Timestamp.now();
        return LedgerEntry.credit(this.id, amount, reference);
    }

    public LedgerEntry debit(Money amount, Description reference) {
        Money newBalance = balance.subtract(amount);
        if (newBalance.isNegative()) {
            throw new IllegalStateException(INSUFFICIENT_FUNDS);
        }
        this.balance = newBalance;
        this.updatedAt = Timestamp.now();
        return LedgerEntry.debit(this.id, amount, reference);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && Objects.equals(accountId, wallet.accountId) && Objects.equals(balance, wallet.balance) && Objects.equals(createdAt, wallet.createdAt) && Objects.equals(updatedAt, wallet.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, balance, createdAt, updatedAt);
    }

}
