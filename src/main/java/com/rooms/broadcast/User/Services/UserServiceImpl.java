package com.rooms.broadcast.User.Services;

import com.rooms.broadcast.User.Dtos.ContactInputDto;
import com.rooms.broadcast.User.Dtos.UserMapper;
import com.rooms.broadcast.User.Dtos.UserOutputDto;
import com.rooms.broadcast.User.Entities.User;
import com.rooms.broadcast.User.Exceptions.UserNotFoundException;
import com.rooms.broadcast.User.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserOutputDto getUser(Long userId)  {
       return userMapper.userToUserOutputDto(getUserById(userId));
    }


    public User getUserById(Long userId){
        Optional<User> optionalUser  = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException(userId);
        }
        return optionalUser.get();
    }

    @Override
    public Set<UserOutputDto> getContacts(Long userId) {
        User user = getUserById(userId);
        return userMapper.userSetToUserOutputDtoSet(user.getContacts());
    }

    /*@Override
    public Set<UserOutputDto> getContacters(Long userId) {
        User user = getUserById(userId);
        return userMapper.userSetToUserOutputDtoSet(user.getContacters());
    }*/

    @Override
    public UserOutputDto addContact(Long userId, ContactInputDto contactInputDto) {
        User user = getUserById(userId);
        User contact = getUserById(contactInputDto.getContactId());
        user.addContact(contact);
        userRepository.save(user);
        return userMapper.userToUserOutputDto(contact);
    }

    @Override
    public Boolean existsInUserContacts(Long ownerId,String username) {
        Optional<User> optionalContact = userRepository.findUserByUsername(username);
        if(optionalContact.isEmpty()){
            return false;
        }
        return userRepository.existsUserByIdAndContactsIsContaining(ownerId, optionalContact.get());
    }

    @Override
    public Boolean isRoomOwnerContact(String username, String roomId){
        return userRepository.isRoomOwnerContact(username, roomId);
    }

}
