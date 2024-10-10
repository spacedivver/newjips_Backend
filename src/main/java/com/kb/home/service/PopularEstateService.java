package com.kb.home.service;

import com.kb.home.dto.PopularEstateDTO;
import com.kb.home.mapper.PopularEstateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PopularEstateService {

    @Autowired
    private PopularEstateMapper estateMapper;

    public List<PopularEstateDTO> getTopWishedEstates() {
        List<PopularEstateDTO> results = estateMapper.findTopWishedEstates();
        List<PopularEstateDTO> estateList = new ArrayList<>();

        for (PopularEstateDTO result : results) {
            PopularEstateDTO estate = new PopularEstateDTO();
            estate.setAddress(result.getAddress());
            estate.setTradetype(result.getTradetype());
            estate.setRoomSize(result.getRoomSize());
            estate.setFloor(result.getFloor());
            estate.setDeposit(result.getDeposit());
            estate.setMonthlyPee(result.getMonthlyPee());
            estate.setImg(result.getImg());

            estateList.add(estate);
        }


        return estateList;
    }
}