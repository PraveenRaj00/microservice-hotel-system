package com.example.HotelService.service;

import com.example.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getOneHotel(String hotelId);

}
