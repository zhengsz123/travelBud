Alter table station drop column gas_price;

ALTER table gasprices
Add column gas_id bigint not null;
ALTER table gasprices ADD CONSTRAINT fk_gasprices_gas
FOREIGN KEY(gas_id) REFERENCES gasprices(id);

ALTER table gasprices
Add column station_id bigint not null;
ALTER table gasprices ADD CONSTRAINT fk_gasprices_station
FOREIGN KEY(station_id) REFERENCES gasprices(id);