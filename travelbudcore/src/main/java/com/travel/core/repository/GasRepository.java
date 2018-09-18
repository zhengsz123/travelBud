package com.travel.core.repository;


import com.travel.core.domain.Gas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasRepository extends CrudRepository<Gas, Long> {

//    @Query("Select g FROM Gas g LEFT JOIN FETCH g.stations where g.id = ?1")
//    Optional<Gas> findByIdWithStations(Long Id);

}
