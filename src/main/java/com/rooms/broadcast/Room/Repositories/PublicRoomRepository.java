package com.rooms.broadcast.Room.Repositories;

import com.rooms.broadcast.Room.Entities.PublicRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicRoomRepository extends JpaRepository<PublicRoom, String> {
    void deleteByOwnerId(String ownerId);
}
