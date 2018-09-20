ALTER TABLE station
    DROP CONSTRAINT fk_station;
ALTER TABLE station
ADD CONSTRAINT fk_station_gas
  FOREIGN KEY(gas_id) REFERENCES station(id)