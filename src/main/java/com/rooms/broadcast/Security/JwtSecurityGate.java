package com.rooms.broadcast.Security;

import com.rooms.broadcast.Room.Dtos.PrivateRoomOutputDto;
import com.rooms.broadcast.Room.Services.PrivateRoomService;
import com.rooms.broadcast.Room.Services.PublicRoomService;
import com.rooms.broadcast.User.Services.UserService;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtSecurityGate implements RoomSecurityGate {

    final UserService userService;
    final PrivateRoomService privateRoomService;
    final PublicRoomService publicRoomService;

    public JwtSecurityGate(UserService userService, PrivateRoomService privateRoomService, PublicRoomService publicRoomService) {
        this.userService = userService;
        this.privateRoomService = privateRoomService;
        this.publicRoomService = publicRoomService;
    }


    @Override
    public boolean hasAccessToPrivateRoomAsOwner(Authentication authentication, String roomId) {
        return isRoomOwner(authentication, roomId);
    }

    @Override
    public boolean hasAccessToPrivateRoom(Authentication authentication, String roomId) {
        if(isRoomOwner(authentication, roomId)){
            return true;
        }
        return userService.isRoomOwnerContact(authentication.getName(), roomId);
    }

    @Override
    public boolean hasAccessToOwnersPrivateRooms(Authentication authentication, Long ownerId) {
        if(isOwner(authentication, ownerId)){
            return true;
        }
        return userService.existsInUserContacts(ownerId, authentication.getName());
    }

    @Override
    public boolean hasAccessToOwnersPrivateRoomsAsOwner(Authentication authentication, Long ownerId) {
        return isOwner(authentication, ownerId);
    }

    @Override
    public boolean hasAccessToPublicRoomAsOwner(SimpMessageHeaderAccessor headerAccessor, String roomId) {
        String sessionId = headerAccessor.getSessionId();
        return publicRoomService.isRoomOwner(roomId, sessionId);
    }


    private boolean isOwner(Authentication authentication, Long ownerId){
        String ownerUsername = userService.getUser(ownerId).getUsername();
        return authentication.getName().equals(ownerUsername);
    }

    private boolean isRoomOwner(Authentication authentication, String roomId){
        PrivateRoomOutputDto privateRoomOutputDto = privateRoomService.getRoom(roomId);
        if(privateRoomOutputDto.getOwnerId()==null){
            return true;
        }
        return userService.getUser(privateRoomOutputDto.getOwnerId())
                .getUsername().equals(authentication.getName());
    }

}
