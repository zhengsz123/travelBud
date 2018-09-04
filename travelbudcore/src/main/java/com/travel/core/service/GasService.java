package com.travel.core.service;


import com.travel.core.domain.Gas;
//import com.travel.core.domain.Station;
import com.travel.core.domain.Station;
import com.travel.core.repository.GasRepository;
import com.travel.core.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class GasService {
    @Autowired
    private GasRepository gasRepository;
    @Transactional(readOnly = true)
    public Gas findBy(Gas g){
        Optional<Gas> optional = gasRepository.findById(g.getId());
        Gas obj = optional.get();
        return  obj;
    }

    @Autowired
    private StationRepository stationRepository;
    public Gas updateGasType(Gas updateGasType){
        gasRepository.save(updateGasType);
        Station station = new Station();
//      station.setGas(updateGasType);
        stationRepository.save(station);

        return updateGasType;
    }



}
