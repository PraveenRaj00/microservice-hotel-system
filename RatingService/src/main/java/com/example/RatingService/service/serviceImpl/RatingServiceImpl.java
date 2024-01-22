package com.example.RatingService.service.serviceImpl;

import com.example.RatingService.entities.Rating;
import com.example.RatingService.repository.RatingsRepository;
import com.example.RatingService.service.RatingService;
import com.example.RatingService.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(cacheNames = "all-ratings")
    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "one-user")
    public List<Rating> getOneByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    @Cacheable(cacheNames = "rating-by-hotelName")
    public List<Rating> getOneByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
