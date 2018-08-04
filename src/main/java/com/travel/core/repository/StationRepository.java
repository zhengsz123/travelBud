package com.travel.core.repository;

import com.travel.core.domain.Station;
import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<Station,String>
{
}
