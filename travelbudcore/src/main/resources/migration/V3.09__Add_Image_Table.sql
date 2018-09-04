CREATE SEQUENCE image_id_seq;


create table image (
    id bigint not null DEFAULT NEXTVAL('image_id_seq'),
    url varchar(255) not NULL,
    primary key (id)
);

ALTER SEQUENCE image_id_seq OWNED BY image.id;