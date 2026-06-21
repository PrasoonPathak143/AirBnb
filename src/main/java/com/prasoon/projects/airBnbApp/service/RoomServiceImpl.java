package com.prasoon.projects.airBnbApp.service;


import com.prasoon.projects.airBnbApp.dto.RoomDto;
import com.prasoon.projects.airBnbApp.repository.HotelRepository;
import com.prasoon.projects.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;

    @Override
    public RoomDto createRoom(Long hotelId, RoomDto roomDto) {
        return null;
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        return List.of();
    }

    @Override
    public RoomDto getRoomById(Long id) {
        return null;
    }

    @Override
    public void deleteRoomById(Long id) {

    }
}
