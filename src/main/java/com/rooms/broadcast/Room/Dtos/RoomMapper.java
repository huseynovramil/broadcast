package com.rooms.broadcast.Room.Dtos;

import com.rooms.broadcast.Room.Entities.PrivateRoom;
import com.rooms.broadcast.Room.Entities.PublicRoom;
import com.rooms.broadcast.Room.QrUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", imports = QrUtil.class)
public interface RoomMapper {
    @Mapping(source = "owner.id", target = "ownerId")
    PrivateRoomOutputDto privateRoomToPrivateRoomOutputDto(PrivateRoom room);

    @Mapping(target = "qr", expression = "java(QrUtil.getQrBase64FromId(room.getId()))")
    PublicRoomOutputDto publicRoomToPublicRoomOutputDto(PublicRoom room);
    Set<PrivateRoomOutputDto> privateRoomSetToPrivateRoomOutputDtoSet(Set<PrivateRoom> roomSet);
}
