CREATE SEQUENCE authority_id_seq;

create table authorities (
    id bigint not null DEFAULT NEXTVAL('authority_id_seq'),
    authority_role varchar(255) not NULL,
    users_id INTEGER,
    primary key (id)
);

ALTER SEQUENCE authority_id_seq OWNED BY authorities.id;
ALTER TABLE authorities
ADD CONSTRAINT fk_authorities_user
  FOREIGN KEY(users_id) REFERENCES authorities(id);