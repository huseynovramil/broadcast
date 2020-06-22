package com.rooms.broadcast.Room.Services;

import com.rooms.broadcast.Room.Dtos.PrivateRoomOutputDto;

import java.util.Set;


public interface PrivateRoomService {
    PrivateRoomOutputDto addRoom(Long ownerId);
    Set<PrivateRoomOutputDto> getRoomsOfUser(Long ownerId);
    PrivateRoomOutputDto getRoom(String roomId);
}
