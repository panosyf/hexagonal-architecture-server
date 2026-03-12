DROP TABLE IF EXISTS ledger_entry;
DROP TABLE IF EXISTS wallet;

CREATE TABLE wallet (
   id VARCHAR,
   account_id VARCHAR(64) NOT NULL,
   balance DECIMAL(19,2) NOT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL,
   CONSTRAINT pk_wallet PRIMARY KEY (id)
);

CREATE TABLE ledger_entry (
   id VARCHAR,
   wallet_id VARCHAR NOT NULL,
   amount DECIMAL(19,2) NOT NULL,
   type VARCHAR,
   reference VARCHAR DEFAULT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL,
   CONSTRAINT pk_ledger_entry PRIMARY KEY (id),
   CONSTRAINT fk_ledger_entry_wallet FOREIGN KEY (wallet_id) REFERENCES wallet(id) ON DELETE CASCADE
);

CREATE INDEX idx_ledger_entry_wallet_id ON ledger_entry(wallet_id);
