package com.kb.cctv.mapper;

import com.kb.cctv.dto.CctvDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CctvMapper {
    List<CctvDTO> getCctvList();

    List<CctvDTO> getCctvByLocation(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
}
