package com.hexagonal.server.ledger.infra.persistence.wallet.entity;

import com.hexagonal.server.ledger.core.wallet.model.LedgerEntryType;
import com.hexagonal.server.shared.kernel.common.persistence.entity.PersistenceEntity;
import com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters.DescriptionAttributeConverter;
import com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters.MoneyAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.*;

@Entity(name = "ledger_entry")
@Table(name = "ledger_entry")
public class LedgerEntryPersistenceEntity extends PersistenceEntity {

    @Column(name = "wallet_id")
    private String walletId;

    @Column(name = "amount")
    @Convert(converter = MoneyAttributeConverter.class)
    private Money amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private LedgerEntryType type;

    @Column(name = "reference")
    @Convert(converter = DescriptionAttributeConverter.class)
    private Description reference;

    protected LedgerEntryPersistenceEntity() {
    }

    public String getWalletId() {
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

    private LedgerEntryPersistenceEntity(
            String id,
            String walletId,
            Money amount,
            LedgerEntryType type,
            Description reference,
            Timestamp createdAt,
            Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.walletId = walletId;
        this.amount = amount;
        this.type = type;
        this.reference = reference;
    }

    public static LedgerEntryPersistenceEntity create(
            String id,
            String walletId,
            Money amount,
            LedgerEntryType type,
            Description reference,
            Timestamp createdAt,
            Timestamp updatedAt) {
        return new LedgerEntryPersistenceEntity(
                id,
                walletId,
                amount,
                type,
                reference,
                createdAt,
                updatedAt);
    }

}
