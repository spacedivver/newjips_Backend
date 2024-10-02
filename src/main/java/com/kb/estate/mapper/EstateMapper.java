package com.kb.estate.mapper;
import com.kb.estate.dto.EstateDTO;
import com.kb.estate.dto.EstateDetailDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EstateMapper {
    List<EstateDTO> getEstateList();

    EstateDetailDTO getEstateDetail(@Param("eno") Long eno);
}
