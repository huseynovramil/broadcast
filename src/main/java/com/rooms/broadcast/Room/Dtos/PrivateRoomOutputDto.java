package com.rooms.broadcast.Room.Dtos;

import com.sun.istack.Nullable;
import lombok.Data;

import java.io.Serializable;

@Data
public class PrivateRoomOutputDto implements Serializable {
    private String id;
    @Nullable
    private Long ownerId;
}
