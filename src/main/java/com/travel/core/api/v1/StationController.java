package com.travel.core.api.v1;

import com.travel.core.domain.Gas;
import com.travel.core.domain.Station;
import com.travel.core.service.GasService;
import com.travel.core.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/station", produces = MediaType.APPLICATION_JSON_VALUE)
public class StationController {


    @Autowired
    private StationService stationService;
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Station updateInfo(@RequestBody Station station){
        stationService.saveUpdate(station);
        return station;
    }

    @Autowired
    private GasService gasService;
    @RequestMapping(value = "station/{id}/gastype",method = RequestMethod.POST)
    @ResponseBody
    public Gas gasType(@RequestBody Gas gas){
        gasService.updateGasType(gas);
        return gas;
    }
}
