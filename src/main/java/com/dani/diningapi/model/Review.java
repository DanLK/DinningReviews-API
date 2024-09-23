package com.dani.diningapi.model;

import com.dani.diningapi.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="REVIEWS")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column(name="AUTHOR_NAME")
    @Getter @Setter private String authorName;

    @Column(name="RESTAURANT_ID")
    @Getter @Setter private Long restaurantId;

    @Column(name="PEANUT_SCORE")
    @Getter @Setter private Integer peanutScore;

    @Column(name="EGG_SCORE")
    @Getter @Setter private Integer eggScore;

    @Column(name="DAIRY_SCORE")
    @Getter @Setter private Integer dairyScore;

    @Column(name="COMMENT")
    @Getter @Setter private String comment;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    @Getter @Setter private ReviewStatus status;

    public Review(Long id,
                  String authorName,
                  Long restaurantId,
                  Integer peanutScore,
                  Integer eggScore,
                  Integer dairyScore,
                  String comment,
                  ReviewStatus status){
        this.id = id;
        this.authorName = authorName;
        this.restaurantId = restaurantId;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;
        this.comment = comment;
        this.status = status;
    }

    public Review(){}
}
