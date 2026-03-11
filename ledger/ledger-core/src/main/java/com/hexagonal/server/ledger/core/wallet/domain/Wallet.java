package com.hexagonal.server.ledger.core.wallet.domain;

import com.hexagonal.server.shared.kernel.common.entity.AggregateRoot;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.hexagonal.server.ledger.core.wallet.exception.WalletErrorMessageConstants.INSUFFICIENT_FUNDS;

public class Wallet extends AggregateRoot {

    private Id id;
    private Id accountId;
    private List<LedgerEntry> ledgerEntryList = new ArrayList<>();
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private Wallet() {
    }

    private Wallet(final Id id, final Id accountId, final List<LedgerEntry> ledgerEntryList) {
        this.id = id;
        this.accountId = accountId;
        this.ledgerEntryList = new ArrayList<>(ledgerEntryList);
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    private Wallet(final Id id, final Id accountId, final List<LedgerEntry> ledgerEntryList, final Timestamp createdAt, final Timestamp updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.ledgerEntryList = new ArrayList<>(ledgerEntryList);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Wallet create(final Id accountId) {
        return new Wallet(Id.generate(), accountId, List.of());
    }

    public static Wallet create(final Id id, final Id accountId, final Timestamp createdAt, final Timestamp updatedAt) {
        return new Wallet(id, accountId, List.of(), createdAt, updatedAt);
    }

    public static Wallet create(final Id id, final Id accountId, final List<LedgerEntry> ledgerEntryList, final Timestamp createdAt, final Timestamp updatedAt) {
        return new Wallet(id, accountId, ledgerEntryList, createdAt, updatedAt);
    }

    public Id getId() {
        return id;
    }

    public Id getAccountId() {
        return accountId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Money balance() {
        return ledgerEntryList.stream()
                .map(LedgerEntry::getAmount)
                .reduce(Money.zero(), Money::add);
    }

    public LedgerEntry credit(Money amount, String reference) {
        LedgerEntry ledgerEntry = LedgerEntry.credit(this.id, amount, reference);
        ledgerEntryList.add(ledgerEntry);
        return ledgerEntry;
    }

    public LedgerEntry debit(Money amount, String reference) {
        Money newBalance = balance().subtract(amount);
        if (newBalance.isNegative()) {
            throw new IllegalStateException(INSUFFICIENT_FUNDS);
        }
        LedgerEntry ledgerEntry = LedgerEntry.debit(this.id, amount, reference);
        ledgerEntryList.add(ledgerEntry);
        return ledgerEntry;
    }

    public List<LedgerEntry> getLedgerEntryList() {
        return Collections.unmodifiableList(ledgerEntryList);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && Objects.equals(accountId, wallet.accountId) && Objects.equals(ledgerEntryList, wallet.ledgerEntryList) && Objects.equals(createdAt, wallet.createdAt) && Objects.equals(updatedAt, wallet.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, ledgerEntryList, createdAt, updatedAt);
    }

}

