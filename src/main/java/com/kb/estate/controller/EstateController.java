package com.kb.estate.controller;

import com.kb.estate.dto.EstateDTO;
import com.kb.estate.dto.EstateDetailDTO;
import com.kb.estate.dto.EstateParam;
import com.kb.estate.service.EstateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Estate", description = "Estate 관련 API")
@RestController
@RequestMapping("/api/estate")
public class EstateController {

    private final EstateService estateService;

    @Autowired
    public EstateController(EstateService estateService) {
        this.estateService = estateService;
    }

    @ApiOperation(value = "매물 리스트 조회", notes = "모든 매물의 리스트를 반환합니다.")
    @GetMapping("/list")
    public List<EstateDTO> getEstateList() {
        return estateService.getEstateList();
    }

    @ApiOperation(value = "특정 매물 정보 조회", notes = "주어진 eno 값을 기반으로 특정 매물의 상세 정보를 반환합니다.")
    @GetMapping("/list/{eno}")
    public EstateDetailDTO getEstateDetail(
            @ApiParam(value = "매물 ID", required = true) @PathVariable("eno") Long eno) {
        return estateService.getEstateDetail(eno);
    }

    @ApiOperation(value = "위치 기반 매물 리스트 조회", notes = "요청된 위도(latitude), 경도(longitude) 기반으로 매물 리스트를 반환합니다.")
    @GetMapping("/location")
    public List<EstateDTO> getEstateByLocation(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        EstateParam estateParam = new EstateParam(latitude, longitude);
        return estateService.getEstateByLocation(estateParam);
    }

}
