ALTER TABLE authorities
    DROP CONSTRAINT fk_authorities_user;
ALTER TABLE authorities
ADD CONSTRAINT fk_authorities_users
  FOREIGN KEY(users_id) REFERENCES authorities(id)