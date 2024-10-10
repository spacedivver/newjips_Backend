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
public class BuddizPageResult {
    private List<Buddiz> buddizList;
    private BuddizParam buddizParam;
    private PageInfo pageInfo;
    private int totalCount;


}
