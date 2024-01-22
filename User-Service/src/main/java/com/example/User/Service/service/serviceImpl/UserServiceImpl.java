package com.example.User.Service.service.serviceImpl;

import com.example.User.Service.entities.Hotel;
import com.example.User.Service.entities.Rating;
import com.example.User.Service.entities.UserTable;
import com.example.User.Service.exceptions.ResourceNotFoundException;
import com.example.User.Service.feignConfig.HotelService;
import com.example.User.Service.feignConfig.RatingService;
import com.example.User.Service.repository.UserRepository;
import com.example.User.Service.service.UserService;
import jakarta.ws.rs.POST;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

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
    @Cacheable(cacheNames = "all-user")
    public List<UserTable> getAllUser() {
        List<UserTable> user= repository.findAll();


        return user;
    }

    @Override
    @Cacheable(cacheNames = "one-user")
    public UserTable getOneUser(String userId) {
        UserTable user= repository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not available on serve !!"+userId));

        logger.info("Requested for User : "+userId+"\n");
        logger.info("---------------------------\n");
        logger.info("Response from server : "+user.toString()+"\n");
        return user;

    }
    

    @Override
    public UserTable updateUserEmail(String email, String userId) {
        UserTable user= repository.findById(userId).orElseThrow(ResourceNotFoundException::new);

        user.setEmail(email);
        repository.save(user);
        return user;
    }

    @Override
    public UserTable updatePartialField(String userId, Map<String, Object> fields) throws ResourceNotFoundException{
        Optional<UserTable> existingDetails= repository.findById(userId);

        if (existingDetails.isPresent()){
            fields.forEach((key, value)-> {
                Field field = ReflectionUtils.findField(UserTable.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,existingDetails.get(),value);
            });
            return repository.save(existingDetails.get());
        }
        return null;


    }
}
