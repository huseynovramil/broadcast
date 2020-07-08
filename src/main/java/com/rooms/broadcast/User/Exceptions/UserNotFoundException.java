package com.rooms.broadcast.User.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("Could not find the user with id " + userId);
    }
}
