
CREATE SEQUENCE gasprices_id_seq;

create table gasprices (
    id bigint not null DEFAULT NEXTVAL('gasprices_id_seq'),
    gas_price double precision ,
    primary key (id)
);

ALTER SEQUENCE gasprices_id_seq OWNED BY gasprices.id;