package com.travel.core.repository;

import com.travel.core.domain.Gas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GasRepository extends CrudRepository<Gas, Long> {
   List<Gas> findAll();

    @Query("Select g FROM Gas g LEFT JOIN FETCH g.stations where g.id = ?1")
    Optional<Gas> findByIdWithStations(Long Id);
}
