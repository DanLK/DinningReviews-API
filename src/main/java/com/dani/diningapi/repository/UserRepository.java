package com.dani.diningapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.dani.diningapi.model.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findByUserName(String userName);
    List<User> findByCity(String city);
}
