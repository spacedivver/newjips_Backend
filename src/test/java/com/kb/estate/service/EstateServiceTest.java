package com.kb.estate.service;
import com.kb._config.ServletConfig;
import com.kb._config.RootConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.estate.dto.EstateDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
@Log4j2
class EstateServiceTest {
    @Autowired
    EstateService estateService;


    @Test
    void getEstateList() {
        List<EstateDTO> list = estateService.getEstateList();
        log.info(list);
    }

    @Test
    void getEstateBrief() {

    }
}