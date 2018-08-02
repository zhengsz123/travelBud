package com.travel.core.repository;

import com.travel.core.config.AppConfig;
import com.travel.core.config.DatabaseConfig;
import com.travel.core.domain.Gas;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class GasRepositoryTest {
    @Autowired
    private GasRepository gasRepository;

    @Test
    @Transactional
    public void findByIdTest(){
        Gas tester = new Gas();
        tester.setGasType("93");
        gasRepository.save(tester);
        Optional<Gas> testGas = gasRepository.findById(tester.getId());
        assertNotNull(testGas);
        assertEquals(tester.getId(),testGas.get().getId());
    }

}
