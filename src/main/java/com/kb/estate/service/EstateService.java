package com.kb.estate.service;

import com.kb.estate.dto.EstateDTO;
import com.kb.estate.dto.EstateDetailDTO;
import com.kb.estate.dto.EstateParam;
import com.kb.estate.mapper.EstateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<EstateDTO> estates = estateMapper.getEstateList();

        // 이미지 URL 리스트 변환
        for (EstateDTO estate : estates) {
            // img 필드가 String이 아닌 List<String>이므로, 직접 접근할 수 없습니다.
            String imgString = estate.getImgString(); // imgString이라는 새로운 getter를 사용해야 함
            if (imgString != null) {
                List<String> imgList = Arrays.asList(imgString.split(","));
                estate.setImg(imgList); // img 필드에 리스트를 설정
            } else {
                estate.setImg(new ArrayList<>()); // img가 null인 경우 빈 리스트로 초기화
            }
        }

        return estates;
    }

    // 특정 매물의 상세 정보를 가져오는 서비스 메서드
    public EstateDetailDTO getEstateDetail(Long eno) {
        EstateDetailDTO estateDetail = estateMapper.getEstateDetail(eno);

        // 이미지 URL 리스트 변환
        String imgString = estateDetail.getImgString(); // imgString이라는 새로운 getter 사용
        if (imgString != null) {
            List<String> imgList = Arrays.asList(imgString.split(","));
            estateDetail.setImg(imgList); // EstateDetailDTO의 img 필드에 리스트를 설정
        } else {
            estateDetail.setImg(new ArrayList<>()); // img가 null인 경우 빈 리스트로 초기화
        }

        return estateDetail;
    }

    public List<EstateDTO> getEstateByLocation(EstateParam estateParam) {
        List<EstateDTO> estates = estateMapper.getEstateByLocation(estateParam); // Pass estateParam as a single object

        // 이미지 URL 리스트 변환 및 설정
        for (EstateDTO estate : estates) {
            String imgString = estate.getImgString();
            if (imgString != null) {
                List<String> imgList = Arrays.asList(imgString.split(","));
                estate.setImg(imgList);
            } else {
                estate.setImg(new ArrayList<>());
            }
        }
        return estates;
    }
}
