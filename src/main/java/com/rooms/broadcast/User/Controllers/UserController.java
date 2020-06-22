package com.rooms.broadcast.User.Controllers;

import com.rooms.broadcast.User.Dtos.ContactInputDto;
import com.rooms.broadcast.User.Dtos.UserOutputDto;
import com.rooms.broadcast.User.Services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    UserOutputDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/users/{id}/contacts")
    Set<UserOutputDto> getContacts(@PathVariable Long id){
        return userService.getContacts(id);
    }

    @PreAuthorize("@jwtSecurityGate.hasAccessToOwnersPrivateRoomsAsOwner(authentication, #id)")
    @PostMapping("/users/{id}/contacts")
    UserOutputDto addContact(@PathVariable Long id, @RequestBody ContactInputDto contactInputDto){
        return userService.addContact(id, contactInputDto);
    }
}
