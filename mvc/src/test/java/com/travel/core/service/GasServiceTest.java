package com.travel.core.service;

import com.travel.core.config.DatabaseConfig;
import com.travel.mvc.config.AppConfig;
import com.travel.core.domain.Gas;
import com.travel.core.repository.GasRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class GasServiceTest {
    @Autowired
    private GasService gasService;
    @Autowired
    private GasRepository gasRepository;

    @Test
    @Transactional
    public void gasServiceTest(){
        Gas g = new Gas();
        g.setGasType("99");
        gasRepository.save(g);
        Gas testG = gasService.findBy(g);
        assertNotNull(testG);
        assertEquals(g.getId(),testG.getId());

    }

}
