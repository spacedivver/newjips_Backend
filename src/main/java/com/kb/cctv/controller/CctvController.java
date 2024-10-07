package com.kb.cctv.controller;

import com.kb.cctv.dto.CctvDTO;
import com.kb.cctv.dto.CctvParam;
import com.kb.cctv.service.CctvService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags ="Cctv", description = "Cctv 관련 API")
@RestController
@RequestMapping("/api/cctv")
public class CctvController {

    private final CctvService cctvService;

    @Autowired
    public CctvController(CctvService cctvService){
        this.cctvService = cctvService;
    }

    @ApiOperation(value = "cctv 리스트 조회", notes = "모든 cctv 리스트 조회")
    @GetMapping("/list")
    public List<CctvDTO> getCctvList(){
        return cctvService.getCctvList();
    }

    @ApiOperation(value = "위칙 기반cctv 리스트 조회", notes = "요청된 위도(latitude), 경도(longitude) 기반으로 cctv 리스트를 반환합니다.")
    @GetMapping("/location")
    public List<CctvDTO> getCctvByLocation
            (@RequestParam Double latitude,
            @RequestParam Double longitude){
        CctvParam cctvParam = new CctvParam(latitude, longitude);
        return cctvService.getCctvByLocation(cctvParam);
    }

}
