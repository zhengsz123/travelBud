ALTER TABLE users Drop COLUMN account_non_expired;
ALTER TABLE users ADD COLUMN account_non_expired BOOLEAN NOT NULL DEFAULT True;