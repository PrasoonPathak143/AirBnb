package com.prasoon.projects.airBnbApp.service;


import com.prasoon.projects.airBnbApp.dto.HotelDto;
import com.prasoon.projects.airBnbApp.dto.RoomDto;
import com.prasoon.projects.airBnbApp.entity.Hotel;
import com.prasoon.projects.airBnbApp.entity.Room;
import com.prasoon.projects.airBnbApp.exception.ResourceNotFoundException;
import com.prasoon.projects.airBnbApp.repository.HotelRepository;
import com.prasoon.projects.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;

    @Override
    public RoomDto createRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room in hotel with id {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));
        Room room = modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        return modelMapper.map(room,RoomDto.class);

        //Todo: Create inventory as soon as room is created and if hotel is active


    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Fetching all rooms in hotel with the hotelID: {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));
        return hotel.getRooms().stream().map((element) -> modelMapper.map(element, RoomDto.class)).collect(Collectors.toList());

    }

    @Override
    public RoomDto getRoomById(Long id) {
        log.info("finding room by roomID: {}",id);
        Room room  = roomRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Room not found with roomID: "+id));
        return modelMapper.map(room,RoomDto.class);



    }

    @Override
    public void deleteRoomById(Long id) {
        log.info("Deleting room by roomID: {}",id);
        Room room = roomRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Room not found with roomid"+id));
        roomRepository.delete(room);
        log.info("Room deleted with roomid: {}",id);

        //TODO:delete all future inventories for this room


    }
}
