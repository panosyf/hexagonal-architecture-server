package com.hexagonal.server.ledger.core.wallet.domain;

import com.hexagonal.server.shared.kernel.common.entity.DomainEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;

import java.util.Objects;

public class LedgerEntry extends DomainEntity {

    private Id id;
    private Id walletId;
    private Money amount;
    private Timestamp createdAt;
    private String reference;

    private LedgerEntry() {
    }

    private LedgerEntry(
            final Id id,
            final Id walletId,
            final Money amount,
            final String reference) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.createdAt = Timestamp.now();
        this.reference = reference;
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

    protected Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getReference() {
        return reference;
    }

    public static LedgerEntry credit(Id walletId, Money amount, String reference) {
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

    public static LedgerEntry debit(Id walletId, Money amount, String reference) {
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
        return Objects.equals(id, that.id) && Objects.equals(walletId, that.walletId) && Objects.equals(amount, that.amount) && Objects.equals(createdAt, that.createdAt) && Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, walletId, amount, createdAt, reference);
    }

    @Override
    public String toString() {
        return "LedgerEntry{" +
                "id=" + id +
                ", walletId=" + walletId +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", reference='" + reference + '\'' +
                '}';
    }

}

