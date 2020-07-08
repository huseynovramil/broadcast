package com.rooms.broadcast.User.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User with username '" + username+"' already exists");
    }
}
