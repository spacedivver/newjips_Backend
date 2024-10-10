package com.kb.home.mapper;

import com.kb.home.dto.PopularEstateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PopularEstateMapper {
    List<PopularEstateDTO> findTopWishedEstates();
}