package com.kb.hotplace.service;

import com.kb.hotplace.dto.HotplaceDTO;
import com.kb.hotplace.mapper.HotplaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotplaceService {
    private final HotplaceMapper hotplaceMapper;

    @Autowired
    public HotplaceService(HotplaceMapper hotplaceMapper){
        this.hotplaceMapper = hotplaceMapper;
    }

    public List<HotplaceDTO> getHotplaceList(){
        return hotplaceMapper.getHotplaceList();
    }
}
