package com.example.HotelService.controller;

import com.example.HotelService.entities.Hotel;
import com.example.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService service;

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
        Hotel hotel1= service.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getALlHotel(){
        List<Hotel> hotelList= service.getAllHotels();
        return ResponseEntity.ok(hotelList);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getOneHotel(@PathVariable String hotelId){
        return ResponseEntity.ok(service.getOneHotel(hotelId));
    }

}
