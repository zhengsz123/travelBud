package com.travel.core.repository;


import com.travel.core.domain.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends CrudRepository<Station,String> {

}
