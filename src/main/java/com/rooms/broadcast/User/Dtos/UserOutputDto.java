package com.rooms.broadcast.User.Dtos;

import lombok.Data;
import org.springframework.core.serializer.Serializer;

import java.io.Serializable;

@Data
public class UserOutputDto implements Serializable {

    private Long id;

    private String username;
}
