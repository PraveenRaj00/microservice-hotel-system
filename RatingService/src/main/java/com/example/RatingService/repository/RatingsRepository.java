package com.example.RatingService.repository;

import com.example.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, String> {

    List<Rating> findByHotelId(String hotelId);

    List<Rating> findByUserId(String userId);

}
