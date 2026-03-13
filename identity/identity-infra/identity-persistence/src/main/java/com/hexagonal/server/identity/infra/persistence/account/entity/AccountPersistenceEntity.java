package com.hexagonal.server.identity.infra.persistence.account.entity;

import com.hexagonal.server.shared.kernel.common.persistence.entity.PersistenceEntity;
import com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters.EmailAttributeConverter;
import com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters.NameAttributeConverter;
import com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters.PasswordAttributeConverter;
import com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters.UsernameAttributeConverter;
import com.hexagonal.server.shared.kernel.common.valueobjects.*;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity(name = "account")
@Table(name = "account")
public class AccountPersistenceEntity extends PersistenceEntity {

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

    protected AccountPersistenceEntity() {
        super();
    }

    private AccountPersistenceEntity(
            String id,
            Email email,
            Username username,
            Password password,
            Name name,
            Timestamp createdAt,
            Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public static AccountPersistenceEntity create(
            String id,
            Email email,
            Username username,
            Password password,
            Name name,
            Timestamp createdAt,
            Timestamp updatedAt) {
        return new AccountPersistenceEntity(id, email, username, password, name, createdAt, updatedAt);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountPersistenceEntity that)) return false;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(email, that.email) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt()) &&
                Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), email, username, password, name, getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "AccountPersistenceEntity{" +
                "id=" + getId() +
                ", email=" + email +
                ", username=" + username +
                ", password=[REDACTED]" +
                ", name=" + name +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}

