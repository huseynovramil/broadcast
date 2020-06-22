package com.rooms.broadcast.Room.Repositories;

import com.rooms.broadcast.Room.Entities.PrivateRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateRoomRepository extends JpaRepository<PrivateRoom, String> {
    Boolean existsRoomByIdAndOwner_Username(String roomId, String ownerUsername);
}
