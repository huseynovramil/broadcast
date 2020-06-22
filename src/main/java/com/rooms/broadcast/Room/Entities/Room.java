package com.rooms.broadcast.Room.Entities;

import com.rooms.broadcast.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Room extends DateAudit {
    @Id
    @GenericGenerator(name = "id", strategy = "com.rooms.broadcast.Room.Entities.RoomIdGenerator")
    @GeneratedValue(generator = "id")
    @Column(length = 7)
    private String id;


    public Room() {
    }

}
