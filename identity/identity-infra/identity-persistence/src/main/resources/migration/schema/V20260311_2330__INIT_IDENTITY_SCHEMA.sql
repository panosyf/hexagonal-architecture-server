DROP TABLE IF EXISTS account;

CREATE TABLE account (
   id VARCHAR,
   email VARCHAR(64) NOT NULL,
   username VARCHAR(64) NOT NULL,
   password VARCHAR(255) NOT NULL,
   name VARCHAR(64) NOT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL,
   CONSTRAINT pk_account PRIMARY KEY (id),
   CONSTRAINT uq_account_email UNIQUE (email),
   CONSTRAINT uq_account_username UNIQUE (username)
);
