package com.kb.cctv.service;

import com.kb.cctv.dto.CctvDTO;
import com.kb.cctv.dto.CctvParam;
import com.kb.cctv.mapper.CctvMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CctvService {
    private final CctvMapper cctvMapper;

    @Autowired
    public CctvService(CctvMapper cctvMapper) {
        this.cctvMapper = cctvMapper;
    }
    //cctvlist all
    public List<CctvDTO> getCctvList(){
        return cctvMapper.getCctvList();
    }

    public List<CctvDTO> getCctvByLocation(CctvParam cctvParam){
        return cctvMapper.getCctvByLocation(cctvParam.getLatitude(), cctvParam.getLongitude());
    }
}
