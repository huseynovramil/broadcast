package com.rooms.broadcast.User.Repositories;

import com.rooms.broadcast.User.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Boolean existsUserByIdAndContactsIsContaining(Long ownerId, User contact);
    @Query("select case when count(c)> 0 then true else false end from User u join u.rooms r join u.contacts c where c.username=:username and r.id=:roomId")
    Boolean isRoomOwnerContact(@Param("username") String username, @Param("roomId") String roomId);

}
