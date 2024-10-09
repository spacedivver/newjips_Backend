package com.kb.buddiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuddizDTO {

    private String reviewContent;
    private long rating;

    public Buddiz toBuddiz() {
        return Buddiz.builder().rating(rating).reviewContent(reviewContent).build();
    }
}
