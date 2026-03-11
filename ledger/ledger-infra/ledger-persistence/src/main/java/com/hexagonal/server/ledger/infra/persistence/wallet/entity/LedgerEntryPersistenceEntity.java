package com.hexagonal.server.ledger.infra.persistence.wallet.entity;

import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.TimestampAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "ledger_entry")
@Table(name = "ledger_entry")
public class LedgerEntryPersistenceEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "wallet_id")
    private String walletId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "reference")
    private String reference;

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

    public String getWalletId() {
        return walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getReference() {
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
            String walletId,
            BigDecimal amount,
            String reference) {

        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.reference = reference;
    }

    public static LedgerEntryPersistenceEntity create(
            String id,
            String walletId,
            BigDecimal amount,
            String reference) {

        return new LedgerEntryPersistenceEntity(
                id,
                walletId,
                amount,
                reference
        );
    }

}
