package com.kb.buddiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuddizParam {
    // html - form의 name과 일치하는 파라메터
    private String searchType;
    private String searchValue;
    private String sort;
    private ArrayList<String> types;

    // 페이징 인자, 요청 할 값
    private int page = 1;

    // mybatis에서 사용 할 limit, offset
    private int limit;
    private int offset;

}
