package com.hexagonal.server.ledger.core.wallet.domain;

import com.hexagonal.server.shared.kernel.common.entity.AggregateRoot;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;

import static com.hexagonal.server.ledger.core.wallet.exception.WalletErrorMessageConstants.INSUFFICIENT_FUNDS;

public class Wallet extends AggregateRoot {

    private Id accountId;
    private Money balance;

    private Wallet() {
    }

    private Wallet(final Id id, final Id accountId) {
        super(id);
        this.accountId = accountId;
        this.balance = Money.zero();
    }

    private Wallet(
            final Id id,
            final Id accountId,
            final Money balance,
            final Timestamp createdAt,
            final Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.balance = balance;
    }

    public static Wallet create(final Id accountId) {
        return new Wallet(Id.generate(), accountId);
    }

    public static Wallet create(
            final Id id,
            final Id accountId,
            final Money balance,
            final Timestamp createdAt,
            final Timestamp updatedAt) {
        return new Wallet(id, accountId, balance, createdAt, updatedAt);
    }

    public Id getAccountId() {
        return accountId;
    }

    public Money getBalance() {
        return balance;
    }

    public LedgerEntry credit(Money amount, Description reference) {
        this.balance = this.balance.add(amount);
        super.touch();
        return LedgerEntry.credit(this.id, amount, reference);
    }

    public LedgerEntry debit(Money amount, Description reference) {
        Money newBalance = balance.subtract(amount);
        if (newBalance.isNegative()) {
            throw new IllegalStateException(INSUFFICIENT_FUNDS);
        }
        this.balance = newBalance;
        super.touch();
        return LedgerEntry.debit(this.id, amount, reference);
    }

}

