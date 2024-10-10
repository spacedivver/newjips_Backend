package com.kb.buddiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private String reviewer;
    private String reviewContent;
    private Date createdAt;
    private long rating;
    private String profilePic;

}
