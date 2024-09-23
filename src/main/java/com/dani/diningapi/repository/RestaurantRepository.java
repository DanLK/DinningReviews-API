package com.dani.diningapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.dani.diningapi.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    List<Restaurant> findByNameAndZipCode(String name, String zipCode);
    List<Restaurant> findByZipCodeAndPeanutScoreIsNotNullOrderByNameDesc(String zipCode);
    List<Restaurant> findByZipCodeAndEggScoreIsNotNullOrderByNameDesc(String zipCode);
    List<Restaurant> findByZipCodeAndDairyScoreIsNotNullOrderByNameDesc(String zipCode);
}
