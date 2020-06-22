package com.rooms.broadcast.Security;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;

public interface RoomSecurityGate {
    boolean hasAccessToPrivateRoomAsOwner(Authentication authentication, String roomId);
    boolean hasAccessToPrivateRoom(Authentication authentication, String roomId);
    boolean hasAccessToOwnersPrivateRooms(Authentication authentication, Long ownerId);
    boolean hasAccessToOwnersPrivateRoomsAsOwner(Authentication authentication, Long ownerId);
    boolean hasAccessToPublicRoomAsOwner(SimpMessageHeaderAccessor headerAccessor, String roomId);
}
