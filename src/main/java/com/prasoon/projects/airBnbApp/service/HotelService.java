package com.prasoon.projects.airBnbApp.service;

import com.prasoon.projects.airBnbApp.dto.HotelDto;
import com.prasoon.projects.airBnbApp.entity.Hotel;

import java.util.List;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    List<HotelDto> getAllHotels();

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);
}
