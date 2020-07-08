package com.rooms.broadcast.Room.Services;

import com.rooms.broadcast.Room.Entities.PrivateRoom;
import com.rooms.broadcast.Room.Dtos.PrivateRoomOutputDto;
import com.rooms.broadcast.Room.Repositories.PrivateRoomRepository;
import com.rooms.broadcast.Room.Dtos.RoomMapper;
import com.rooms.broadcast.Room.Exceptions.RoomNotFoundException;
import com.rooms.broadcast.User.Entities.User;
import com.rooms.broadcast.User.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PrivateRoomServiceImpl implements PrivateRoomService {

    final UserService userService;
    final PrivateRoomRepository privateRoomRepository;
    final RoomMapper roomMapper;

    public PrivateRoomServiceImpl(UserService userService, PrivateRoomRepository privateRoomRepository, RoomMapper roomMapper) {
        this.userService = userService;
        this.privateRoomRepository = privateRoomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public PrivateRoomOutputDto addRoom(Long ownerId) {
        PrivateRoom room;
        if(ownerId!=null){
            User user = userService.getUserById(ownerId);
            room = new PrivateRoom(user);
        }
        else {
            room = new PrivateRoom();
        }
        privateRoomRepository.save(room);
        return roomMapper.privateRoomToPrivateRoomOutputDto(room);

    }

    @Override
    public Set<PrivateRoomOutputDto> getRoomsOfUser(Long ownerId) {
        User user = userService.getUserById(ownerId);
        return roomMapper.privateRoomSetToPrivateRoomOutputDtoSet(user.getRooms());
    }

    @Override
    public PrivateRoomOutputDto getRoom(String roomId) {
        Optional<PrivateRoom> optionalPrivateRoom = privateRoomRepository.findById(roomId);
        if(optionalPrivateRoom.isEmpty()){
            throw new RoomNotFoundException(roomId);
        }
        return roomMapper.privateRoomToPrivateRoomOutputDto(optionalPrivateRoom.get());
    }

}
