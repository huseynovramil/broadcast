package com.rooms.broadcast.Room.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PublicRoom extends Room {
    private String ownerId;

    public PublicRoom() {
        super();
    }

}
