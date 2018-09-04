package com.travel.core.service;


import com.travel.core.domain.Station;
import com.travel.core.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StationService {
    @Autowired
    private StationRepository stationRepository;
    public Station save(Station station){
        stationRepository.save(station);
        return null;
    }

}
