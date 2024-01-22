package com.example.User.Service.controller;

import com.example.User.Service.entities.Hotel;
import com.example.User.Service.entities.Rating;
import com.example.User.Service.entities.UserTable;
import com.example.User.Service.feignConfig.HotelService;
import com.example.User.Service.feignConfig.RatingService;
import com.example.User.Service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.hibernate.dialect.function.array.ArraySliceUnnestFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<UserTable> saveUser(@RequestBody UserTable userTable){
        UserTable user= service.saveUser(userTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @GetMapping
    public ResponseEntity<List<UserTable>> getAllUser(){
        List<UserTable> allUsers= service.getAllUser();
        return ResponseEntity.ok(allUsers);
    }


    @GetMapping("/{userId}")
    @CircuitBreaker(name = "user-service",fallbackMethod = "handleRatingDowntime")
    public ResponseEntity<UserTable> getOneUser(@PathVariable String userId){

        UserTable user= service.getOneUser(userId);
        List<Rating> rating= ratingService.getUserRating(user.getUserId());

        List<Rating> ratingList= rating.stream().map(ratings -> {
            Hotel hotel =hotelService.getHotel(ratings.getHotelId());
            ratings.setHotel(hotel);
            return ratings;
        }).toList();
        //user.setRatings(ratingService.getUserRating(user.getUserId()));

        user.setRatings(ratingList);
        return ResponseEntity.ok(user);
    }


    public ResponseEntity<UserTable> handleRatingDowntime(String userId, Exception e){



        UserTable user= service.getOneUser(userId);

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<UserTable> updateUserEmail(@RequestBody String email, @PathVariable String userId){

        return ResponseEntity.ok(service.updateUserEmail(email,userId));
    }

    @PatchMapping("/partial-update/{userId}")
    public ResponseEntity<UserTable> updatePartialField(@PathVariable String userId, @RequestBody Map<String, Object> fields){
        return ResponseEntity.ok(service.updatePartialField(userId,fields));
    }
}
