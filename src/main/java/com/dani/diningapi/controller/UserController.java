package com.dani.diningapi.controller;

import com.dani.diningapi.model.User;
import com.dani.diningapi.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/findByCity")
    public List<User> getUsersByCity(@RequestParam(name="city", required = false) String city){
        if (city != null){
            return this.userRepository.findByCity(city);
        }
        return new ArrayList<>();
    }

    @PostMapping("/addNew")
    public String createNewUser(@RequestBody User newUser){
        if (this.userRepository.findByUserName(newUser.getUserName()).isEmpty()) {
            Pattern dutchZipPattern = Pattern.compile("^[1-9][0-9]{3}\s?(?!sa|sd|ss)[a-z]{2}", Pattern.CASE_INSENSITIVE);
            if (!dutchZipPattern.matcher(newUser.getZipCode()).matches()){
                return "Error. Invalid Zip Code.";
            }
            this.userRepository.save(newUser);
            return "New user created successfully";
        }
        return "Cannot create new user. User name already taken.";
    }

    @PutMapping("/edit/{userName}")
    public String updateExistingUser(@PathVariable String userName, @RequestBody User updatedUser){
        List <User> userList = this.userRepository.findByUserName(userName);
        if (userList.size() != 1){
            return "Error. User not found.";
        }
        User userToUpdate = userList.get(0);
        userToUpdate.setCity(updatedUser.getCity());
        userToUpdate.setCountry(updatedUser.getCountry());
        String zipCode = updatedUser.getZipCode();
        Pattern dutchZipPattern = Pattern.compile("^[1-9][0-9]{3}\s?(?!sa|sd|ss)[a-z]{2}", Pattern.CASE_INSENSITIVE);
        if(!dutchZipPattern.matcher(zipCode).matches()){
            return "Error. Invalid Zip Code.";
        }
        userToUpdate.setZipCode(updatedUser.getZipCode());
        userToUpdate.setIsInterestedInPeanut(updatedUser.getIsInterestedInPeanut());
        userToUpdate.setIsInterestedInEgg(updatedUser.getIsInterestedInEgg());
        userToUpdate.setIsInterestedInDairy(updatedUser.getIsInterestedInDairy());
        this.userRepository.save(userToUpdate);
        return "User updated successfully";
    }

}
