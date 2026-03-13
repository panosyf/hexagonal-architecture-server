package com.hexagonal.server.ledger.core.wallet.domain;

import com.hexagonal.server.ledger.core.wallet.model.LedgerEntryType;
import com.hexagonal.server.shared.kernel.common.entity.DomainEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;

public class LedgerEntry extends DomainEntity {

    private Id walletId;
    private Money amount;
    private LedgerEntryType type;
    private Description reference;

    private LedgerEntry() {
    }

    private LedgerEntry(
            final Id id,
            final Id walletId,
            final Money amount,
            final LedgerEntryType type,
            final Description reference) {
        super(id);
        this.walletId = walletId;
        this.amount = amount;
        this.type = type;
        this.reference = reference;
    }

    private LedgerEntry(
            final Id id,
            final Id walletId,
            final Money amount,
            final LedgerEntryType type,
            final Description reference,
            final Timestamp createdAt,
            final Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.walletId = walletId;
        this.amount = amount;
        this.type = type;
        this.reference = reference;
    }

    public static LedgerEntry create(
            Id id,
            Id walletId,
            Money amount,
            LedgerEntryType type,
            Description reference,
            Timestamp createdAt,
            Timestamp updatedAt) {
        return new LedgerEntry(
                id,
                walletId,
                amount,
                type,
                reference,
                createdAt,
                updatedAt);
    }

    protected static LedgerEntry credit(Id walletId, Money amount, Description reference) {
        if (amount.isNegative()) {
            throw new IllegalArgumentException("Credit cannot be negative");
        }
        return new LedgerEntry(
                Id.generate(),
                walletId,
                amount,
                LedgerEntryType.CREDIT,
                reference);
    }

    protected static LedgerEntry debit(Id walletId, Money amount, Description reference) {
        if (amount.isNegative()) {
            throw new IllegalArgumentException("Debit amount cannot be negative");
        }
        return new LedgerEntry(
                Id.generate(),
                walletId,
                Money.of(amount.getValue().negate(), amount.getCurrency()),
                LedgerEntryType.DEBIT,
                reference);
    }

    public Id getWalletId() {
        return walletId;
    }

    public Money getAmount() {
        return amount;
    }

    public LedgerEntryType getType() {
        return type;
    }

    public Description getReference() {
        return reference;
    }

}


