package com.prasoon.projects.airBnbApp.repository;

import com.prasoon.projects.airBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    //Room -Entity with which this RoomRepository will be dealing and Long is primary key datatype for Room Entity


}
