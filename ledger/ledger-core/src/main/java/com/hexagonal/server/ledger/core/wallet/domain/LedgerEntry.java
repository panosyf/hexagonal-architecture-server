package com.hexagonal.server.ledger.core.wallet.domain;

import com.hexagonal.server.shared.kernel.common.entity.DomainEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;

import java.util.Objects;

public class LedgerEntry extends DomainEntity {

    private Id id;
    private Id walletId;
    private Money amount;
    private Description reference;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private LedgerEntry() {
    }

    private LedgerEntry(
            final Id id,
            final Id walletId,
            final Money amount,
            final Description reference) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.reference = reference;
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    private LedgerEntry(
            final Id id,
            final Id walletId,
            final Money amount,
            final Description reference,
            final Timestamp createdAt,
            final  Timestamp updatedAt) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.reference = reference;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static LedgerEntry create(final Id id, final Id walletId, Money amount, Description reference) {
        return new LedgerEntry(id, walletId, amount, reference);
    }

    public static LedgerEntry create(final Id id, final Id walletId, Money amount, Description reference, Timestamp createdAt, Timestamp updatedAt) {
        return new LedgerEntry(id, walletId, amount, reference, createdAt, updatedAt);
    }

    public Id getId() {
        return id;
    }

    public Id getWalletId() {
        return walletId;
    }

    public Money getAmount() {
        return amount;
    }

    public Description getReference() {
        return reference;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected static LedgerEntry credit(Id walletId, Money amount, Description reference) {
        if (amount.isNegative()) {
            throw new IllegalArgumentException("Credit cannot be negative");
        }
        return new LedgerEntry(
                Id.generate(),
                walletId,
                amount,
                reference
        );
    }

    protected static LedgerEntry debit(Id walletId, Money amount, Description reference) {
        if (amount.isNegative()) {
            throw new IllegalArgumentException("Debit amount cannot be negative");
        }
        return new LedgerEntry(
                Id.generate(),
                walletId,
                Money.of(amount.getValue().negate(), amount.getCurrency()),
                reference
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LedgerEntry that = (LedgerEntry) o;
        return Objects.equals(id, that.id) && Objects.equals(walletId, that.walletId) && Objects.equals(amount, that.amount) && Objects.equals(reference, that.reference) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, walletId, amount, reference, createdAt, updatedAt);
    }

}

