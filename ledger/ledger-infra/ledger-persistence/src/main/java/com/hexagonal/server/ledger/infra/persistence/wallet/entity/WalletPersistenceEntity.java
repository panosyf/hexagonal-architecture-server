package com.hexagonal.server.ledger.infra.persistence.wallet.entity;

import com.hexagonal.server.shared.kernel.common.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "wallet")
@Table(name = "wallet")
public class WalletPersistenceEntity extends PersistenceEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "account_id")
    private String accountId;

    protected WalletPersistenceEntity() {
    }

    private WalletPersistenceEntity(String id, String accountId) {
        this.id = id;
        this.accountId = accountId;
    }

    public static WalletPersistenceEntity create(String id, String accountId) {
        return new WalletPersistenceEntity(id, accountId);
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }
}
