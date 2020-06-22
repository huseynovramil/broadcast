package com.rooms.broadcast.Room.Exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String roomId) {
        super("Could not find the room with id '" + roomId+"'");
    }
}
