package com.example.RatingService.controller;

import com.example.RatingService.entities.Rating;
import com.example.RatingService.service.serviceImpl.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingServiceImpl service;


    @PostMapping
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRating(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.ok(service.getAllRatings());
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<Rating>> getByRating(@PathVariable String userID){
        return ResponseEntity.ok(service.getOneByUserId(userID));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(service.getOneByHotelId(hotelId));
    }
}
