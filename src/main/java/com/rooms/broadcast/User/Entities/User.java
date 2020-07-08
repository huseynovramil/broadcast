package com.rooms.broadcast.User.Entities;

import com.rooms.broadcast.DateAudit;
import com.rooms.broadcast.Room.Entities.PrivateRoom;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_contacts",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "contact_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    @EqualsAndHashCode.Exclude
    private Set<User> contacts = new HashSet<>();


    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<PrivateRoom> rooms = new HashSet<>();

    public void addContact(User contact){
        this.contacts.add(contact);
    }

    public void removeContact(User contact){
        this.contacts.remove(contact);
    }

    public void addRoom(PrivateRoom room){
        this.rooms.add(room);
        room.setOwner(this);
    }
    public void removeRoom(PrivateRoom room){
        room.setOwner(null);
        this.rooms.remove(room);
    }
}
