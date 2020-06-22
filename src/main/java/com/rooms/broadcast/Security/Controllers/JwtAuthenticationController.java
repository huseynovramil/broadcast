package com.rooms.broadcast.Security.Controllers;

import com.rooms.broadcast.Security.Dtos.JwtRequest;
import com.rooms.broadcast.Security.Dtos.JwtResponse;
import com.rooms.broadcast.Security.Jwt.JwtTokenUtil;
import com.rooms.broadcast.Security.Services.CustomUserDetails;
import com.rooms.broadcast.Security.Services.JwtUserDetailsService;
import com.rooms.broadcast.User.Dtos.UserInputDto;
import com.rooms.broadcast.User.Dtos.UserOutputDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest);

        return new JwtResponse(token);
    }

    @PostMapping(value = "/register")
    public UserOutputDto saveUser(@RequestBody UserInputDto userInputDto) {
        return userDetailsService.save(userInputDto);
    }

}