ALTER TABLE station
  DROP COLUMN gas_id;
ALTER TABLE station
  ADD COLUMN gas_id bigint DEFAULT NULL,
  ADD CONSTRAINT fk_station
  FOREIGN KEY(gas_id) REFERENCES station(id);