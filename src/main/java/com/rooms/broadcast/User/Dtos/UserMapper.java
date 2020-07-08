package com.rooms.broadcast.User.Dtos;

import com.rooms.broadcast.User.Entities.User;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserOutputDto userToUserOutputDto(User user);

    User userInputDtoToUser(UserInputDto userInputDto);

    Set<UserOutputDto> userSetToUserOutputDtoSet(Set<User> userSet);
}
