package com.alpha.momentum.controller;

import com.alpha.momentum.constants.RoutingConstants;
import com.alpha.momentum.enums.Operations;
import com.alpha.momentum.enums.Status;
import com.alpha.momentum.model.CommonResponse;
import com.alpha.momentum.model.UserRequest;
import com.alpha.momentum.model.UserResponse;
import com.alpha.momentum.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(path = RoutingConstants.USERS_API_URL_WITH_PATH_PARAM, method = RequestMethod.GET)
    public ResponseEntity<UserResponse> fetchUser(@PathVariable(name = "userId") Long id) {
        UserResponse userResponse = service.retrieveUserById(id);
        return null == userResponse ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @RequestMapping(path = RoutingConstants.USERS_API_URL_WITH_PATH_PARAM, method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> updateUser(@PathVariable(name = "userId") Long id,
                                                   @RequestBody @Valid UserRequest userRequest) {
        UserResponse updatedUser = service.updateUserById(id, userRequest);
        return null == updatedUser ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @RequestMapping(path = RoutingConstants.USERS_API_URL_WITH_PATH_PARAM, method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@PathVariable(name = "userId") Long id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CommonResponse(Operations.DELETE, Status.SUCCESSFUL, "User removed successfully :" + id,
                            null));
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CommonResponse(Operations.DELETE, Status.FAILED, "User not found with Id :" + id,
                            exception.getClass().toString()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse(Operations.DELETE, Status.FAILED, "Unexpected error occurred" + id,
                            exception.getClass().toString() + " " + exception.getMessage()));
        }
    }
}
