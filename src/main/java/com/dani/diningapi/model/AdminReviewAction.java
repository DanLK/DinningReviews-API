package com.dani.diningapi.model;

import lombok.Getter;
import lombok.Setter;

public class AdminReviewAction {

    @Getter @Setter private Review review;
    @Getter @Setter private Boolean isApproved;

    public AdminReviewAction(Review review, Boolean isApproved){
        this.review = review;
        this.isApproved = isApproved;
    }

}
