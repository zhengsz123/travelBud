CREATE SEQUENCE station_id_seq;


create table station (
    id bigint not null DEFAULT NEXTVAL('station_id_seq'),
    gas_price varchar(255),
    gas_location varchar(255),
    gas_brand varchar(255) not NULL,
    primary key (id)
);

ALTER SEQUENCE gas_id_seq OWNED BY gas.id;
