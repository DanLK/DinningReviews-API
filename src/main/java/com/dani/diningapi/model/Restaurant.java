package com.dani.diningapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Entity
@Table(name="RESTAURANTS")
public class Restaurant {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @Column(name="NAME")
    @Getter @Setter private String name;

    @Column(name="CITY")
    @Getter @Setter private String city;

    @Column(name="ZIP_CODE")
    @Getter @Setter private String zipCode;

    @Column(name="CAPACITY")
    @Getter @Setter private Integer capacity;

    @Column(name="PEANUT_SCORE")
    @Getter @Setter private Double peanutScore;

    @Column(name="EGG_SCORE")
    @Getter @Setter private Double eggScore;

    @Column(name="DAIRY_SCORE")
    @Getter @Setter private Double dairyScore;

    @Column(name="OVERALL_SCORE")
    @Getter @Setter private Double overallScore;

    public Restaurant(String name,
                      String city,
                      String zipCode,
                      Integer capacity,
                      Double peanutScore,
                      Double eggScore,
                      Double dairyScore,
                      Double overallScore){
        this.name = name;
        this.city = city;
        this.zipCode = zipCode;
        this.capacity = capacity;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;
        this.overallScore = overallScore;
    }

    public Restaurant() {

    }

    public void updatePeanutScore(Integer newScore, Integer count){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (this.peanutScore == null){
            this.peanutScore = Double.valueOf(decimalFormat.format(newScore));
        }
        else {
            Double newPeanutScore = ((this.peanutScore * Double.valueOf(count)) + newScore) / (Double.valueOf(count) + 1);
            this.peanutScore = Double.valueOf(decimalFormat.format(newPeanutScore));
        }
    }

    public void updateEggScore(Integer newScore, Integer count){
        if (this.eggScore == null){
            this.eggScore = Double.valueOf(newScore);
        }
        else{
            this.eggScore = ((this.eggScore * Double.valueOf(count)) + newScore) / (Double.valueOf(count) + 1);
        }
    }

    public void updateDairyScore(Integer newScore, Integer count){
        if (this.dairyScore == null){
            this.dairyScore = Double.valueOf(newScore);
        }
        else {
            this.dairyScore = ((this.dairyScore * Double.valueOf(count)) + newScore) / (Double.valueOf(count) + 1);
        }
    }

    public void updateOverallScore(){
        this.overallScore = (this.peanutScore + this.eggScore + this.dairyScore) / 3;
    }

}
