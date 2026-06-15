package com.prasoon.projects.airBnbApp.service;

import com.prasoon.projects.airBnbApp.dto.HotelDto;
import com.prasoon.projects.airBnbApp.entity.Hotel;
import com.prasoon.projects.airBnbApp.exception.ResourceNotFoundException;
import com.prasoon.projects.airBnbApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
    //first method receives HotelDto, then converts DTO to entity with ModelMapper,then save this object to database where spring JPA calls Hibernate, Hibernate generates SQL and postgreSql inserts the row, then again it converts entity to DTO and service returns HotelDTO to controller.
    //
    //Request JSON  -  HotelDto  -  Hotel Entity  -  Database  -  Hotel Entity -  HotelDto  -  Response JSON

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

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating hotel with id - {}", id);
        Hotel data = hotelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Hotel with id " + id +  " not found")
        );
        modelMapper.map(hotelDto, data);
        data.setId(id);
        data = hotelRepository.save(data);
        return modelMapper.map(data, HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long id) {
        boolean exists = hotelRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Hotel with id " + id +  " not found");
        hotelRepository.deleteById(id);

        // TODO: delete the future inventories for this hotel

    }
}
