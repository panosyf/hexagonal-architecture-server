package com.hexagonal.server.ledger.infra.persistence.wallet.entity;

import com.hexagonal.server.shared.kernel.common.entity.PersistenceEntity;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.IdAttributeConverter;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.TimestampAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.*;

@Entity(name = "wallet")
@Table(name = "wallet")
public class WalletPersistenceEntity extends PersistenceEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "account_id")
    @Convert(converter = IdAttributeConverter.class)
    private com.hexagonal.server.shared.kernel.common.valueobjects.Id accountId;

    @Column(name = "created_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp updatedAt;

    protected WalletPersistenceEntity() {
    }

    private WalletPersistenceEntity(String id, com.hexagonal.server.shared.kernel.common.valueobjects.Id accountId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static WalletPersistenceEntity create(String id, com.hexagonal.server.shared.kernel.common.valueobjects.Id accountId, Timestamp createdAt, Timestamp updatedAt) {
        return new WalletPersistenceEntity(id, accountId, createdAt, updatedAt);
    }

    public String getId() {
        return id;
    }

    public com.hexagonal.server.shared.kernel.common.valueobjects.Id getAccountId() {
        return accountId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

}
