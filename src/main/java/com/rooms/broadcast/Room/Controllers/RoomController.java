package com.rooms.broadcast.Room.Controllers;

import com.rooms.broadcast.Room.Dtos.PrivateRoomOutputDto;
import com.rooms.broadcast.Room.Dtos.PublicRoomOutputDto;
import com.rooms.broadcast.Room.Dtos.Broadcast;
import com.rooms.broadcast.Room.Services.PrivateRoomService;
import com.rooms.broadcast.Room.Services.PublicRoomService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RoomController {

    private  final PrivateRoomService privateRoomService;
    private final PublicRoomService publicRoomService;

    public RoomController(PrivateRoomService privateRoomService, PublicRoomService publicRoomService) {
        this.privateRoomService = privateRoomService;
        this.publicRoomService = publicRoomService;
    }


    @PreAuthorize("@jwtSecurityGate.hasAccessToOwnersPrivateRooms(authentication, #id)")
    @GetMapping(value = "/users/{id}/rooms")
    public Set<PrivateRoomOutputDto> getRoomsOfUser(@PathVariable Long id){
        return privateRoomService.getRoomsOfUser(id);
    }


    @PreAuthorize("@jwtSecurityGate.hasAccessToOwnersPrivateRoomsAsOwner(authentication, #id)")
    @PostMapping(value = "/users/{id}/rooms")
    public PrivateRoomOutputDto addRoom(@PathVariable Long id){
       return privateRoomService.addRoom(id);
    }


    @PreAuthorize("@jwtSecurityGate.hasAccessToPrivateRoom(authentication, #id)")
    @GetMapping(value = "/rooms/{id}")
    public PrivateRoomOutputDto getRoom(Authentication authentication, @PathVariable String id){

        return privateRoomService.getRoom(id);
    }


    @PostMapping(value = "/rooms")
    public PublicRoomOutputDto addRoom(){
        return publicRoomService.addRoom();
    }


    @PreAuthorize("@jwtSecurityGate.hasAccessToPrivateRoomAsOwner(authentication, #id)")
    @MessageMapping(value = "/rooms/private/{id}")
    public Broadcast sendPrivateData(@DestinationVariable String id, Broadcast broadcast){
        return broadcast;
    }


    @PreAuthorize("@jwtSecurityGate.hasAccessToPublicRoomAsOwner(#headerAccessor, #id)")
    @MessageMapping(value = "/rooms/public/{id}")
    public Broadcast sendPublicData(@DestinationVariable String id, Broadcast broadcast, SimpMessageHeaderAccessor headerAccessor){
        return broadcast;
    }


    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
