package com.kb.buddiz.dto;

import com.kb.common.pagination.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewList {
    private List<Review> ReviewList;
    private int totalCount;
    private double avg;

}
