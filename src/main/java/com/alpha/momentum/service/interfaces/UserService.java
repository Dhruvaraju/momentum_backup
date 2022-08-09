package com.alpha.momentum.service.interfaces;

import com.alpha.momentum.model.UserRequest;
import com.alpha.momentum.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponse addNewUser(UserRequest request);

    List<UserResponse> retrieveAllUsers();

    UserResponse retrieveUserById(Long id);

    UserResponse updateUserById(Long id, UserRequest request);

    void deleteUser(Long id);
}
