package com.hexagonal.server.shared.kernel.common.entity;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;

public abstract class AggregateRoot {

    protected Id id;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    protected AggregateRoot() {
    }

    protected AggregateRoot(final Id id) {
        this.id = id;
        Timestamp now = Timestamp.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    protected AggregateRoot(final Id id, final Timestamp createdAt, final Timestamp updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected void touch() {
        this.updatedAt = Timestamp.now();
    }

    public Id getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

}
