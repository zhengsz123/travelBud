ALTER TABLE users ADD COLUMN password varchar(255) NOT NULL DEFAULT 'defaultpassword123';
ALTER TABLE users ADD COLUMN account_non_expired varchar(255) NOT NULL DEFAULT True;
ALTER TABLE users ADD COLUMN account_non_locked BOOLEAN NOT NULL DEFAULT True;
ALTER TABLE users ADD COLUMN credential_non_expired BOOLEAN NOT NULL DEFAULT True;
ALTER TABLE users ADD COLUMN enable_or_not BOOLEAN NOT NULL DEFAULT True;
