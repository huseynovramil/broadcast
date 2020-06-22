package com.rooms.broadcast.Security.Services;

import java.util.ArrayList;
import java.util.Optional;

import com.rooms.broadcast.User.Dtos.UserInputDto;
import com.rooms.broadcast.User.Dtos.UserMapper;
import com.rooms.broadcast.User.Dtos.UserOutputDto;
import com.rooms.broadcast.User.Entities.User;
import com.rooms.broadcast.User.Exceptions.UserAlreadyExistsException;
import com.rooms.broadcast.User.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username) ;
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User user = userOptional.get();
        return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getId(), new ArrayList<>());
    }

    public UserOutputDto save(UserInputDto userInputDto) {
        if(userRepository.findUserByUsername(userInputDto.getUsername()).isPresent()){
            throw new UserAlreadyExistsException(userInputDto.getUsername());
        }
        userInputDto.setPassword(bcryptEncoder.encode(userInputDto.getPassword()));
        User user = userMapper.userInputDtoToUser(userInputDto);
        return userMapper.userToUserOutputDto(userRepository.save(user));
    }
}
