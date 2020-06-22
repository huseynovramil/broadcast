package com.rooms.broadcast.User.Services;

import com.rooms.broadcast.User.Dtos.ContactInputDto;
import com.rooms.broadcast.User.Dtos.UserOutputDto;
import com.rooms.broadcast.User.Entities.User;

import java.util.Set;

public interface UserService {
    User getUserById(Long userId);
    UserOutputDto getUser(Long userId);
    Set<UserOutputDto> getContacts(Long userId);
    UserOutputDto addContact(Long userId, ContactInputDto contactInputDto);
    Boolean existsInUserContacts(Long ownerId,String username);
    Boolean isRoomOwnerContact(String username, String roomId);
}
