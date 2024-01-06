package com.example.User.Service.service.serviceImpl;

import com.example.User.Service.entities.Hotel;
import com.example.User.Service.entities.Rating;
import com.example.User.Service.entities.UserTable;
import com.example.User.Service.exceptions.ResourceNotFoundException;
import com.example.User.Service.feignConfig.HotelService;
import com.example.User.Service.feignConfig.RatingService;
import com.example.User.Service.repository.UserRepository;
import com.example.User.Service.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private HotelService hotelService;

    @Override
    public UserTable saveUser(UserTable user) {

        String randomId= UUID.randomUUID().toString();
        user.setUserId(randomId);
        return repository.save(user);
    }

    @Override
    public List<UserTable> getAllUser() {
        return repository.findAll();
    }

    @Override
    public UserTable getOneUser(String userId) {
        UserTable user= repository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not available on serve !!"+userId));

        List<Rating> rating= ratingService.getUserRating(user.getUserId());

        List<Rating> ratingList= rating.stream().map(ratings -> {
        Hotel hotel =hotelService.getHotel(ratings.getHotelId());
        ratings.setHotel(hotel);
        return ratings;
        }).toList();
        //user.setRatings(ratingService.getUserRating(user.getUserId()));

        user.setRatings(ratingList);
        return user;

    }
}
