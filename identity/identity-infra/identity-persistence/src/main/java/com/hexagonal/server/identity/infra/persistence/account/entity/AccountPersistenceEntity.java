package com.hexagonal.server.identity.infra.persistence.account.entity;

import com.hexagonal.server.shared.kernel.common.entity.PersistenceEntity;
import com.hexagonal.server.shared.kernel.common.infra.valueobjects.converters.*;
import com.hexagonal.server.shared.kernel.common.valueobjects.*;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity(name = "account")
@Table(name = "account")
public class AccountPersistenceEntity extends PersistenceEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    @Convert(converter = EmailAttributeConverter.class)
    private Email email;

    @Column(name = "username")
    @Convert(converter = UsernameAttributeConverter.class)
    private Username username;

    @Column(name = "password")
    @Convert(converter = PasswordAttributeConverter.class)
    private Password password;

    @Column(name = "name")
    @Convert(converter = NameAttributeConverter.class)
    private Name name;

    @Column(name = "created_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Convert(converter = TimestampAttributeConverter.class)
    private Timestamp updatedAt;

    protected AccountPersistenceEntity() {
    }

    public AccountPersistenceEntity(
            final String id,
            final Email email,
            final Username username,
            final Password password,
            final Name name,
            final Timestamp createdAt,
            final Timestamp updatedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
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
        AccountPersistenceEntity that = (AccountPersistenceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, name, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "AccountPersistenceEntity{" +
                "id=" + id +
                ", email=" + email +
                ", username=" + username +
                ", password=" + "[REDACTED]" +
                ", name=" + name +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
