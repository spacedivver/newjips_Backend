package com.kb.hotplace.controller;

import com.kb.hotplace.dto.HotplaceDTO;
import com.kb.hotplace.service.HotplaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags ="Hotplace", description = "Hotplace 관련 API")
@RestController
@RequestMapping("/api/hotplace")
public class HotplaceController {

    private final HotplaceService hotplaceService;

    @Autowired
    private HotplaceController(HotplaceService hotplaceService) {
        this.hotplaceService = hotplaceService;
    }
    @ApiOperation(value = "핫플 리스트 조회", notes = "모든 핫플 리스트 조회")
    @GetMapping("/list")
    public List<HotplaceDTO> getHotplaceList(){
        return hotplaceService.getHotplaceList();
    }

}
