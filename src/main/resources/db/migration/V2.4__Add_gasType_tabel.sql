DROP table gas;

CREATE SEQUENCE gas_id_seq;


create table gas (
    id bigint not null DEFAULT NEXTVAL('gas_id_seq'),
    gas_type varchar(255) not NULL,
    primary key (id)
);

ALTER SEQUENCE gas_id_seq OWNED BY gas.id;

