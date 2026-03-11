-- TODO MOVE MIGRATIONS PER MODULE, HANDLE BEING RUNNING BY TIMESTAMPS
--DROP TABLE IF EXISTS "transaction";
DROP TABLE IF EXISTS "account";

CREATE TABLE "account" (
   id VARCHAR,
   email VARCHAR(64) NOT NULL,
   username VARCHAR(64) NOT NULL,
   password VARCHAR(64) NOT NULL,
   name VARCHAR(64) NOT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL,
   PRIMARY KEY (id)
);

-- TODO CREATE FOR WALLET AND LEDGER ENTRY
--CREATE TABLE "transaction" (
--   id VARCHAR,
--   type VARCHAR(64) NOT NULL,
--   amount DECIMAL(19,2) NOT NULL,
--   description VARCHAR DEFAULT NULL,
--   debtor_account_id VARCHAR,
--   beneficiary_account_id VARCHAR,
--   status VARCHAR(64) NOT NULL,
--   created_at TIMESTAMP NOT NULL,
--   updated_at TIMESTAMP NOT NULL,
--   PRIMARY KEY (id),
--   CONSTRAINT FK_DebtorAccountTransaction FOREIGN KEY (debtor_account_id) REFERENCES "account"(id) ON DELETE CASCADE,
--   CONSTRAINT FK_BeneficiaryAccountTransaction FOREIGN KEY (beneficiary_account_id) REFERENCES "account"(id) ON DELETE CASCADE
--);