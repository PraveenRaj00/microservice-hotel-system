package com.example.RatingService.service.serviceImpl;

import com.example.RatingService.entities.Rating;
import com.example.RatingService.repository.RatingsRepository;
import com.example.RatingService.service.RatingService;
import com.example.RatingService.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {



    @Autowired
    RatingsRepository repository;

    @Override
    public Rating saveRating(Rating rating) {

        String randomId= UUID.randomUUID().toString();
        rating.setRatingId(randomId);

        return repository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getOneByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getOneByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
