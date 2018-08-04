package com.travel.core.service;

import com.travel.core.domain.Station;
import com.travel.core.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;
    @Transactional(readOnly = true)
    public void saveUpdate(Station station){
        stationRepository.save(station);
    }
}
