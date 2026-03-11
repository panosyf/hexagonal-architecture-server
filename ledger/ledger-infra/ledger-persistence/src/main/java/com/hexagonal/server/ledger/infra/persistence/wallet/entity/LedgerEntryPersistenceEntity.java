package com.hexagonal.server.ledger.infra.persistence.wallet.entity;

import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.DescriptionAttributeConverter;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.IdAttributeConverter;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.MoneyAttributeConverter;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.TimestampAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.*;

@Entity(name = "ledger_entry")
@Table(name = "ledger_entry")
public class LedgerEntryPersistenceEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "wallet_id")
    @Convert(converter = IdAttributeConverter.class)
    private com.hexagonal.server.shared.kernel.common.valueobjects.Id walletId;

    @Column(name = "amount")
    @Convert(converter = MoneyAttributeConverter.class)
    private Money amount;

    @Column(name = "reference")
    @Convert(converter = DescriptionAttributeConverter.class)
    private Description reference;

    @Column(name = "created_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp updatedAt;

    protected LedgerEntryPersistenceEntity() {
    }

    public String getId() {
        return id;
    }

    public com.hexagonal.server.shared.kernel.common.valueobjects.Id getWalletId() {
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

    private LedgerEntryPersistenceEntity(
            String id,
            com.hexagonal.server.shared.kernel.common.valueobjects.Id walletId,
            Money amount,
            Description reference) {

        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.reference = reference;
    }

    public static LedgerEntryPersistenceEntity create(
            String id,
            com.hexagonal.server.shared.kernel.common.valueobjects.Id walletId,
            Money amount,
            Description reference) {

        return new LedgerEntryPersistenceEntity(
                id,
                walletId,
                amount,
                reference
        );
    }

}
