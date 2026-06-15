package com.prasoon.projects.airBnbApp.dto;


import com.prasoon.projects.airBnbApp.entity.Hotel;
import lombok.Data;


import java.math.BigDecimal;


@Data
public class RoomDto {
    private Long id;
    //private Hotel hotel;
    // Note: Making above as commented as we don't want to see hotel complete details along
    // with the room details that's why we did  @ManyToOne(fetch = FetchType.LAZY) in Room.java.In case
    // we need hotel details as well then we have to make it as @ManyToOne(fetch = FetchType.Eager) in Room.java as well.

    private String type;
    private BigDecimal basePrice;
    private String[] photos;
    private String[] amenities;
    private Integer totalCount;
    private Integer capacity;
}
