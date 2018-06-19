package com.travel.core.service;

import com.travel.core.domain.Gas;
import com.travel.core.repository.GasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GasService {
    @Autowired
    private GasRepository gasRepository;

    public Gas findBy(Gas g){
        Optional<Gas> optional = gasRepository.findById(g.getId());
        Gas obj = optional.get();
        return  obj;
    }


}
