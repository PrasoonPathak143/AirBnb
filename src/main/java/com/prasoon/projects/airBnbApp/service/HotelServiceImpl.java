package com.prasoon.projects.airBnbApp.service;

import com.prasoon.projects.airBnbApp.dto.HotelDto;
import com.prasoon.projects.airBnbApp.entity.Hotel;
import com.prasoon.projects.airBnbApp.exception.ResourceNotFoundException;
import com.prasoon.projects.airBnbApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating new hotel name - {}", hotelDto.getName());
        Hotel entity = modelMapper.map(hotelDto, Hotel.class);
        entity.setActive(false);
        Hotel savedHotel = hotelRepository.save(entity);
        return modelMapper.map(savedHotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Fetching hotel with id - {}", id);
        Hotel data = hotelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Hotel with id " + id +  " not found")
        );
        return modelMapper.map(data, HotelDto.class);

    }

    @Override
    public List<HotelDto> getAllHotels() {
        log.info("Fetching all hotels");
        return hotelRepository.findAll()
                .stream().map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .toList();

    }
}
