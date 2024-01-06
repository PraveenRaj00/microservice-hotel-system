package com.example.HotelService.serviceImpl;

import com.example.HotelService.entities.Hotel;
import com.example.HotelService.exceptions.ResourceNotFoundException;
import com.example.HotelService.repository.HotelRepository;
import com.example.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository repository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomId= UUID.randomUUID().toString();
        hotel.setHotelId(randomId);

        return repository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    @Override
    public Hotel getOneHotel(String hotelId) {
        return repository.findById(hotelId).orElseThrow(() -> new
                ResourceNotFoundException("Resource not available on server !!"+hotelId));
    }
}
