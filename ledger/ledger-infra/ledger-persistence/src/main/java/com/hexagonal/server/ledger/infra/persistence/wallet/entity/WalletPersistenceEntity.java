package com.hexagonal.server.ledger.infra.persistence.wallet.entity;

import com.hexagonal.server.shared.kernel.common.persistence.entity.PersistenceEntity;
import com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters.MoneyAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "wallet")
@Table(name = "wallet")
public class WalletPersistenceEntity extends PersistenceEntity {

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "balance")
    @Convert(converter = MoneyAttributeConverter.class)
    private Money balance;

    protected WalletPersistenceEntity() {
    }

    private WalletPersistenceEntity(
            String id,
            String accountId,
            Money balance,
            Timestamp createdAt,
            Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.balance = balance;
    }

    public static WalletPersistenceEntity create(
            String id,
            String accountId,
            Money balance,
            Timestamp createdAt,
            Timestamp updatedAt) {
        return new WalletPersistenceEntity(
                id,
                accountId,
                balance,
                createdAt,
                updatedAt);
    }

    public String getAccountId() {
        return accountId;
    }

    public Money getBalance() {
        return balance;
    }

}

