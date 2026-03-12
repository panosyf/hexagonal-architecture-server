package com.hexagonal.server.ledger.infra.persistence.wallet.entity;

import com.hexagonal.server.shared.kernel.common.entity.PersistenceEntity;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.MoneyAttributeConverter;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.TimestampAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.*;

@Entity(name = "wallet")
@Table(name = "wallet")
public class WalletPersistenceEntity extends PersistenceEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "balance")
    @Convert(converter = MoneyAttributeConverter.class)
    private Money balance;

    @Column(name = "created_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp updatedAt;

    protected WalletPersistenceEntity() {
    }

    private WalletPersistenceEntity(String id, String accountId, Money balance, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static WalletPersistenceEntity create(String id, String accountId, Money balance, Timestamp createdAt, Timestamp updatedAt) {
        return new WalletPersistenceEntity(id, accountId, balance, createdAt, updatedAt);
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public Money getBalance() {
        return balance;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

}
