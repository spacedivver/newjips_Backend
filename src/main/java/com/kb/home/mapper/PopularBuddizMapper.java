package com.kb.home.mapper;

import com.kb.home.dto.PopularBuddizDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PopularBuddizMapper {
    // 리뷰가 10개 이상이고 별점이 높은 상위 3명을 조회하는 메서드
    List<PopularBuddizDTO> findTopBuddiz();
}