package com.dani.diningapi.controller;

import com.dani.diningapi.enums.Allergy;
import com.dani.diningapi.model.Restaurant;
import com.dani.diningapi.repository.RestaurantRepository;
import com.dani.diningapi.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;


    public RestaurantController(final RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id){
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(id);
        return restaurant.orElse(null);
    }

    @GetMapping("/restaurants/search")
    public List<Restaurant> getRestaurantsByZipAndAllergy(@RequestParam(name="zipCode") String zipCode, @RequestParam(name="allergy")String allergy){
        if (zipCode != null && allergy != null){
            if (allergy.equals("peanut")){
                return this.restaurantRepository.findByZipCodeAndPeanutScoreIsNotNullOrderByNameDesc(zipCode);
            }
            if (allergy.equals("egg")){
                return this.restaurantRepository.findByZipCodeAndEggScoreIsNotNullOrderByNameDesc(zipCode);
            }
            if (allergy.equals("dairy")){
                return this.restaurantRepository.findByZipCodeAndDairyScoreIsNotNullOrderByNameDesc(zipCode);
            }
        }
        return new ArrayList<>();
    }

    @PostMapping("/restaurants/addNew")
    public String addNewRestaurant(@RequestBody Restaurant newRestaurant){
        if (newRestaurant != null){
            if (this.restaurantRepository.findByNameAndZipCode(newRestaurant.getName(), newRestaurant.getZipCode()).isEmpty()){
                Pattern dutchZipPattern = Pattern.compile("^[1-9][0-9]{3}\s?(?!sa|sd|ss)[a-z]{2}", Pattern.CASE_INSENSITIVE);
                if (!dutchZipPattern.matcher(newRestaurant.getZipCode()).matches()){
                    return "Error. Invalid Zip Code.";
                }
                this.restaurantRepository.save(newRestaurant);
                return "Restaurant successfully added.";
            }
            return "Restaurant with the same name and zip code already exists.";
        }
        return "Could not add an empty restaurant.";
    }

}
