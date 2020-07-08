package com.rooms.broadcast.Client;

import com.rooms.broadcast.Room.Services.PrivateRoomService;
import com.rooms.broadcast.Room.Services.PublicRoomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    final PrivateRoomService privateRoomService;
    final PublicRoomService publicRoomService;

    public HomeController(PrivateRoomService privateRoomService, PublicRoomService publicRoomService) {
        this.privateRoomService = privateRoomService;
        this.publicRoomService = publicRoomService;
    }

    @PreAuthorize("@jwtSecurityGate.hasAccessToPrivateRoom(authentication, #roomId)")
    @RequestMapping(value = "/rooms/private/{roomId}")
    public String PrivateRoom(@PathVariable("roomId") String roomId, Model model, Authentication authentication){
        return "index";
    }

    @RequestMapping(value = "/rooms/public/{roomId}")
    public String PublicRoom(@PathVariable("roomId") String roomId, Model model){
        publicRoomService.getRoom(roomId);
        return "index";
    }

}
