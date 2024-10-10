package com.kb.home.controller;
import com.kb.home.dto.PopularEstateDTO;
import com.kb.home.service.PopularEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PopularEstateController {
    @Autowired
    private PopularEstateService estateService;

    @RequestMapping(value = "/api/popular-estate", method = RequestMethod.GET)
    @ResponseBody
    public List<PopularEstateDTO> getTopWishedEstates() {
        return estateService.getTopWishedEstates();
    }
}