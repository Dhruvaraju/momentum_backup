package com.alpha.momentum.service.interfaces;

import com.alpha.momentum.model.UserRequest;
import com.alpha.momentum.model.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponse addNewUser(UserRequest request);
}
