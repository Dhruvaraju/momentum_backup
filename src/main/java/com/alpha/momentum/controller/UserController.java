package com.alpha.momentum.controller;

import com.alpha.momentum.constants.RoutingConstants;
import com.alpha.momentum.model.CommonResponse;
import com.alpha.momentum.model.UserRequest;
import com.alpha.momentum.model.UserResponse;
import com.alpha.momentum.service.UserServiceImpl;
import com.alpha.momentum.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = RoutingConstants.API_VERSION)
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @RequestMapping(path = RoutingConstants.USERS_API_URL, method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse savedUser = service.addNewUser(userRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userID}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @RequestMapping(path = RoutingConstants.USERS_API_URL, method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> fetchAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.retrieveAllUsers());
    }
}
