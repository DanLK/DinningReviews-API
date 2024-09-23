package com.dani.diningapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column(name="USER_NAME")
    @Getter @Setter private String userName;

    @Column(name="CITY")
    @Getter @Setter private String city;

    @Column(name="COUNTRY")
    @Getter @Setter private String country;

    @Column(name="ZIP_CODE")
    @Getter @Setter private String zipCode;

    @Column(name="IS_INTERESTED_IN_PEANUT")
    @Getter @Setter private Boolean isInterestedInPeanut;

    @Column(name="IS_INTERESTED_IN_EGG")
    @Getter @Setter private Boolean isInterestedInEgg;

    @Column(name="IS_INTERESTED_IN_DAIRY")
    @Getter @Setter private Boolean isInterestedInDairy;

    public User(String userName,
                String city,
                String country,
                String zipCode,
                Boolean isInterestedInPeanut,
                Boolean isInterestedInEgg,
                Boolean isInterestedInDairy){
        this.userName = userName;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.isInterestedInPeanut = isInterestedInPeanut;
        this.isInterestedInEgg = isInterestedInEgg;
        this.isInterestedInDairy = isInterestedInDairy;
    }

    public User(){

    }
}
