package com.prasoon.projects.airBnbApp.controller;


import com.prasoon.projects.airBnbApp.dto.RoomDto;
import com.prasoon.projects.airBnbApp.entity.Room;
import com.prasoon.projects.airBnbApp.service.RoomService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@PathVariable Long hotelId,@RequestBody RoomDto roomDto){

        RoomDto roomdto = roomService.createRoom(hotelId, roomDto);
        return new ResponseEntity<>(roomdto, HttpStatus.CREATED);

    }

     @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){
         List<RoomDto> allRoomsInHotel = roomService.getAllRoomsInHotel(hotelId);
         return new ResponseEntity<>(allRoomsInHotel, HttpStatus.OK);

     }

     @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId,@PathVariable Long hotelId){
         RoomDto roomById = roomService.getRoomById(roomId);
         return new ResponseEntity<>(roomById, HttpStatus.OK);
     }

     @DeleteMapping("/{roomId}")
     public ResponseEntity<Void> deleteRoomById(@PathVariable Long roomId,@PathVariable Long hotelId) {
       roomService.deleteRoomById(roomId);
       return ResponseEntity.noContent().build();

     }


}
