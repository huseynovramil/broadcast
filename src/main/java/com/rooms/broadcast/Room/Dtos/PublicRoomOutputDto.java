package com.rooms.broadcast.Room.Dtos;

import lombok.Data;

@Data
public class PublicRoomOutputDto {
    private String id;
    private byte[] qr;
}
