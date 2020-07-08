package com.rooms.broadcast.Room.Services;

import com.rooms.broadcast.Room.Dtos.PublicRoomOutputDto;
import com.rooms.broadcast.Room.Entities.PublicRoom;

public interface PublicRoomService {
    PublicRoomOutputDto addRoom();
    Boolean isRoomOwner(String roomId, String ownerId);
    void setOwner(String roomId, String ownerId);
    void removeRoomWithOwner(String sessionId);
    PublicRoomOutputDto getRoom(String roomId);
}
