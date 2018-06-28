ALTER TABLE users Drop COLUMN password;
ALTER TABLE users Drop COLUMN account_non_expired;
ALTER TABLE users Drop COLUMN account_non_locked;
ALTER TABLE users Drop COLUMN credential_non_expired;
ALTER TABLE users Drop COLUMN enable_or_not;

ALTER TABLE users ADD COLUMN password varchar(255) NOT NULL DEFAULT 'defaultPassword';
ALTER TABLE users ADD COLUMN account_expired BOOlEAN NOT NULL DEFAULT FALSE;
ALTER TABLE users ADD COLUMN account_locked BOOLEAN NOT NULL DEFAULT FALSE ;
ALTER TABLE users ADD COLUMN credential_expired BOOLEAN NOT NULL DEFAULT FALSE ;
ALTER TABLE users ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT True;