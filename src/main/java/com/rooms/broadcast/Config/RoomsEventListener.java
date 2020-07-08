package com.rooms.broadcast.Config;

import com.rooms.broadcast.Room.Services.PublicRoomService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

public class RoomsEventListener {

    final PublicRoomService publicRoomService;

    public RoomsEventListener(PublicRoomService publicRoomService) {
        this.publicRoomService = publicRoomService;
    }

    @EventListener
    public void handleSessionConnected(SessionSubscribeEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String address = headers.getDestination();
        if (address != null) {
            String roomId = address.substring(address.lastIndexOf("/") + 1);
            String sessionId = headers.getSessionId();
            publicRoomService.setOwner(roomId, sessionId);
        }
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        publicRoomService.removeRoomWithOwner(headers.getSessionId());
    }
}
