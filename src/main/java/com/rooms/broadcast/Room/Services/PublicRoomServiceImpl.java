package com.rooms.broadcast.Room.Services;

import com.rooms.broadcast.Room.Dtos.PublicRoomOutputDto;
import com.rooms.broadcast.Room.Entities.PublicRoom;
import com.rooms.broadcast.Room.Repositories.PublicRoomRepository;
import com.rooms.broadcast.Room.Dtos.RoomMapper;
import com.rooms.broadcast.Room.Exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class PublicRoomServiceImpl implements PublicRoomService {
    final PublicRoomRepository publicRoomRepository;

    final RoomMapper roomMapper;


    @Autowired
    public PublicRoomServiceImpl(PublicRoomRepository publicRoomRepository, RoomMapper roomMapper) {
        this.publicRoomRepository = publicRoomRepository;
        this.roomMapper = roomMapper;
    }


    @Override
    public PublicRoomOutputDto addRoom() {
        PublicRoom room = new PublicRoom();

        return roomMapper.publicRoomToPublicRoomOutputDto(publicRoomRepository.save(room));
    }

    @Override
    public void setOwner(String roomId, String ownerId){
        PublicRoom publicRoom = getRoomById(roomId);
        if(publicRoom.getOwnerId()==null) {
            publicRoom.setOwnerId(ownerId);
            publicRoomRepository.save(publicRoom);
        }
    }

    private PublicRoom getRoomById(String roomId){
        Optional<PublicRoom> optionalPublicRoom = publicRoomRepository.findById(roomId);
        if(optionalPublicRoom.isEmpty()){
            throw new RoomNotFoundException(roomId);
        }
        return optionalPublicRoom.get();
    }

    public PublicRoomOutputDto getRoom(String roomId){
      return roomMapper.publicRoomToPublicRoomOutputDto(getRoomById(roomId));
    }

    @Override
    public Boolean isRoomOwner(String roomId, String ownerId) {
        PublicRoom publicRoom = getRoomById(roomId);
        if(publicRoom.getOwnerId() == null){

        }
        return publicRoom.getOwnerId().equals(ownerId);
    }

    @Override
    public void removeRoomWithOwner(String sessionId) {
        publicRoomRepository.deleteByOwnerId(sessionId);
    }
}
