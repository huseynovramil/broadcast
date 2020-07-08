package com.rooms.broadcast.Room.Entities;

import com.rooms.broadcast.User.Entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PrivateRoom extends Room {
    public PrivateRoom() {
        super();
    }

    public PrivateRoom(User owner) {
        super();
        this.owner = owner;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User owner;
}
