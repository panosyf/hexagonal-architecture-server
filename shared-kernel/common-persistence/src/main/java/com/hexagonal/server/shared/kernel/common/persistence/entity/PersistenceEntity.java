package com.hexagonal.server.shared.kernel.common.persistence.entity;

import com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters.TimestampAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistenceEntity {

    @Id
    @Column(name = "id")
    protected String id;

    @Column(name = "created_at")
    @Convert(converter = TimestampAttributeConverter.class)
    protected Timestamp createdAt;

    @Column(name = "updated_at")
    @Convert(converter = TimestampAttributeConverter.class)
    protected Timestamp updatedAt;

    protected PersistenceEntity() {
    }

    protected PersistenceEntity(String id, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

}

