package com.example.User.Service.feignConfig;

import com.example.User.Service.entities.Rating;
import lombok.Getter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    @GetMapping("/ratings/user/{userId}")
    List<Rating> getUserRating(@PathVariable String userId);

}
