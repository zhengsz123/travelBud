package com.travel.core.repository;

import com.travel.core.config.AppConfig;
import com.travel.core.config.DatabaseConfig;
import com.travel.core.domain.Media;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class StorageRepositoryTest {
    @Autowired
    private MediaRepository mediaRepository;

    @Test
    @Transactional
    public void findByMediaIdTest(){
        Media tester = new Media();
        tester.setUrl("lalalala");
        tester.setS3Key("lalalla.pdf");
        mediaRepository.save(tester);
        Optional<Media> testMedia = mediaRepository.findKeyandUrlById(tester.getId());
        assertNotNull(testMedia);
        assertEquals(tester.getId(),testMedia.get().getId());
    }
}
