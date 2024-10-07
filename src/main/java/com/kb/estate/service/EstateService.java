package com.kb.estate.service;

import com.kb.estate.dto.EstateDTO;
import com.kb.estate.dto.EstateDetailDTO;
import com.kb.estate.dto.EstateParam;
import com.kb.estate.mapper.EstateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateService {

    private final EstateMapper estateMapper;

    @Autowired
    public EstateService(EstateMapper estateMapper) {
        this.estateMapper = estateMapper;
    }

    // 매물 리스트를 가져오는 서비스 메서드
    public List<EstateDTO> getEstateList() {
        return estateMapper.getEstateList();
    }

    // 특정 매물의 상세 정보를 가져오는 서비스 메서드
    public EstateDetailDTO getEstateDetail(Long eno) {
        return estateMapper.getEstateDetail(eno);
    }

    public List<EstateDTO> getEstateByLocation(EstateParam estateParam) {
        return estateMapper.getEstateByLocation(estateParam.getLatitude(), estateParam.getLongitude());
    }

}
