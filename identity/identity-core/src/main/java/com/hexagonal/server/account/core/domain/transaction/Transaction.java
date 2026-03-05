package com.hexagonal.server.account.core.domain.transaction;

import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionType;
import com.hexagonal.server.shared.kernel.common.entity.DomainEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;

import java.util.Objects;

public class Transaction extends DomainEntity {

    private Id id;
    private TransactionType type;
    private Money amount;
    private Description description;
    private Id debtorAccountId;
    private Id beneficiaryAccountId;
    private TransactionStatusEnum status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private Transaction() {
    }

    public Transaction(
            final TransactionType type,
            final Money amount,
            final Description description,
            final Id debtorAccountId,
            final Id beneficiaryAccountId,
            final TransactionStatusEnum status) {
        this.id = Id.generate();
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.debtorAccountId = debtorAccountId;
        this.beneficiaryAccountId = beneficiaryAccountId;
        this.status = status;
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public Transaction(
            final Id id,
            final TransactionType type,
            final Money amount,
            final Description description,
            final Id debtorAccountId,
            final Id beneficiaryAccountId,
            final TransactionStatusEnum status) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.debtorAccountId = debtorAccountId;
        this.beneficiaryAccountId = beneficiaryAccountId;
        this.status = status;
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
        ;
    }

    public Transaction(
            final Id id,
            final TransactionType type,
            final Money amount,
            final Description description,
            final Id debtorAccountId,
            final Id beneficiaryAccountId,
            final TransactionStatusEnum status,
            final Timestamp createdAt,
            final Timestamp updatedAt) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.debtorAccountId = debtorAccountId;
        this.beneficiaryAccountId = beneficiaryAccountId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Id getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Money getAmount() {
        return amount;
    }

    public Description getDescription() {
        return description;
    }

    public Id getDebtorAccountId() {
        return debtorAccountId;
    }

    public Id getBeneficiaryAccountId() {
        return beneficiaryAccountId;
    }

    public TransactionStatusEnum getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void updateStatus(TransactionStatusEnum status) {
        this.status = status;
        this.updatedAt = Timestamp.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && type == that.type && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(debtorAccountId, that.debtorAccountId) && Objects.equals(beneficiaryAccountId, that.beneficiaryAccountId) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, amount, description, debtorAccountId, beneficiaryAccountId, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", description=" + description +
                ", debtorAccountId=" + debtorAccountId +
                ", beneficiaryAccountId=" + beneficiaryAccountId +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
