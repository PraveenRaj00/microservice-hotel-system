package com.example.RatingService.service;

import com.example.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getOneByUserId(String userID);

    List<Rating> getOneByHotelId(String hotelId);
    
}
