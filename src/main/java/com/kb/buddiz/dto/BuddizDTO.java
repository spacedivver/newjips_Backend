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
//    private long likeUno;
//    private long likedUno;
    private long reviewerUno;
    private long uno;
    private long wished_id;

    public Buddiz toBuddiz() {
        return Buddiz.builder().rating(rating).reviewContent(reviewContent).reviewerUno(reviewerUno).uno(uno).build();
    }
    public Buddiz toReview() {
        return Buddiz.builder().uno(uno).wished_id(wished_id).build();
    }

}
