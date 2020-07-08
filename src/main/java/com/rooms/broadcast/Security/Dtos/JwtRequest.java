package com.rooms.broadcast.Security.Dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
    private String platform;
    private String uuid;

    public JwtRequest()
    {

    }

    public JwtRequest(String username, String password, String platform, String uuid) {
        this.username = username;
        this.password = password;
        this.platform = platform;
        this.uuid = uuid;
    }


}