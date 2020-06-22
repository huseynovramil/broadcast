package com.rooms.broadcast.User.Dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInputDto implements Serializable {
    private String username;
    private String password;

}
