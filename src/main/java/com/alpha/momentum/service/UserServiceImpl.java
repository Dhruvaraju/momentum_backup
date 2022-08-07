package com.alpha.momentum.service;

import com.alpha.momentum.entities.UserEntity;
import com.alpha.momentum.model.UserRequest;
import com.alpha.momentum.model.UserResponse;
import com.alpha.momentum.repository.UserRepository;
import com.alpha.momentum.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    public UserResponse addNewUser(UserRequest request) {
        UserEntity entity = mapUserRequestToUserEntity(request, null);
        UserEntity createdUser = repository.save(entity);
        return mapUserEntityToUserResponse(createdUser);
    }

    public List<UserResponse> retrieveAllUsers() {
        return mapUserEntityListToUserResponseList(repository.findAll());
    }

    private UserEntity mapUserRequestToUserEntity(UserRequest request, UserEntity userEntity) {
        UserEntity entity = new UserEntity();
        if (null != userEntity) {
            entity = userEntity;
        }
        if (null != request) {
            if (null != request.getUserName()) {
                entity.setUserName(request.getUserName());
            }
            if (null != request.getFirstName()) {
                entity.setFirstName(request.getFirstName());
            }
            if (null != request.getLastName()) {
                entity.setLastName(request.getLastName());
            }
            if (null != request.getEmail()) {
                entity.setEmail(request.getEmail());
            }
            if (null != request.getPassword()) {
                entity.setPassword(request.getPassword());
            }
            if (null != request.getRole()) {
                entity.setRole(request.getRole());
            }
        }
        return entity;
    }

    private List<UserResponse> mapUserEntityListToUserResponseList(List<UserEntity> entityList) {
        List<UserResponse> userResponseList = new ArrayList<>();
        if (entityList.size() > 0) {
            for (UserEntity entity : entityList) {
                UserResponse userResponse = mapUserEntityToUserResponse(entity);
                userResponseList.add(userResponse);
            }
            return userResponseList;
        } else {
            return new ArrayList<>();
        }
    }

    private UserResponse mapUserEntityToUserResponse(UserEntity entity) {
        return new UserResponse(entity.getId(), entity.getUserName(), entity.getFirstName(), entity.getLastName(),
                entity.getEmail(), entity.getRole());
    }
}
