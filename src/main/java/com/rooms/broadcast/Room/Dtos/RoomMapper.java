package com.rooms.broadcast.Room.Dtos;

import com.rooms.broadcast.Room.Entities.PrivateRoom;
import com.rooms.broadcast.Room.Entities.PublicRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(source = "owner.id", target = "ownerId")
    PrivateRoomOutputDto privateRoomToPrivateRoomOutputDto(PrivateRoom room);
    PublicRoomOutputDto publicRoomToPublicRoomOutputDto(PublicRoom room);
    Set<PrivateRoomOutputDto> privateRoomSetToPrivateRoomOutputDtoSet(Set<PrivateRoom> roomSet);

}
