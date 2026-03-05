package com.hexagonal.server.identity.infra.persistence.entity.transaction;

import com.hexagonal.server.identity.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.identity.core.model.enums.transaction.TransactionType;
import com.hexagonal.server.shared.kernel.common.entity.PersistenceEntity;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.DescriptionAttributeConverter;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.MoneyAttributeConverter;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.TimestampAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "transaction")
@Table(name = "transaction")
public class TransactionPersistenceEntity extends PersistenceEntity {

    @Column(name = "id")
    @Id
    private String id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "amount")
    @Convert(converter = MoneyAttributeConverter.class)
    private Money amount;

    @Column(name = "description")
    @Convert(converter = DescriptionAttributeConverter.class)
    private Description description;

    @Column(name = "debtor_account_id")
    private String debtorAccountId;

    @Column(name = "beneficiary_account_id")
    private String beneficiaryAccountId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum status;

    @Column(name = "created_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp updatedAt;

    protected TransactionPersistenceEntity() {
    }

    public TransactionPersistenceEntity(
            String id,
            TransactionType type,
            Money amount,
            Description description,
            String debtorAccountId,
            String beneficiaryAccountId,
            TransactionStatusEnum status,
            Timestamp createdAt,
            Timestamp updatedAt) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getDebtorAccountId() {
        return debtorAccountId;
    }

    public void setDebtorAccountId(String debtorAccountId) {
        this.debtorAccountId = debtorAccountId;
    }

    public String getBeneficiaryAccountId() {
        return beneficiaryAccountId;
    }

    public void setBeneficiaryAccountId(String beneficiaryAccountId) {
        this.beneficiaryAccountId = beneficiaryAccountId;
    }

    public TransactionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransactionStatusEnum status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionPersistenceEntity that = (TransactionPersistenceEntity) o;
        return Objects.equals(id, that.id) && type == that.type && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(debtorAccountId, that.debtorAccountId) && Objects.equals(beneficiaryAccountId, that.beneficiaryAccountId) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, amount, description, debtorAccountId, beneficiaryAccountId, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "TransactionPersistenceEntity{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", debtorAccountId='" + debtorAccountId + '\'' +
                ", beneficiaryAccountId='" + beneficiaryAccountId + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
