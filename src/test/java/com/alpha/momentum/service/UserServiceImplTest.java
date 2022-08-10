package com.alpha.momentum.service;

import com.alpha.momentum.entities.UserEntity;
import com.alpha.momentum.enums.UserType;
import com.alpha.momentum.model.UserRequest;
import com.alpha.momentum.model.UserResponse;
import com.alpha.momentum.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    private UserRequest request;
    private UserResponse response;
    private UserEntity entity;
    private UserEntity requestEntity;
    private List<UserEntity> userEntityList = new ArrayList<>();

    private static final Long ID = 1L;
    private static final String USERNAME = "Alpha";
    private static final String FIRSTNAME = "Jason";
    private static final String LASTNAME = "Bourne";
    private static final String EMAIL = "jason@identity.net";
    private static final String PASSWORD = "bi01bu03bs02";
    private static final UserType ROLE = UserType.Admin;

    @BeforeEach
    void setUp() {
        request = new UserRequest(USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, ROLE);
        response = new UserResponse(ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, ROLE);
        requestEntity = new UserEntity();
        requestEntity.setUserName(USERNAME);
        requestEntity.setFirstName(FIRSTNAME);
        requestEntity.setLastName(LASTNAME);
        requestEntity.setEmail(EMAIL);
        requestEntity.setPassword(PASSWORD);
        requestEntity.setRole(ROLE);
        entity = new UserEntity(ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, ROLE);
        userEntityList.add(entity);
    }

    @Tag("happy-path")
    @Tag("create")
    @DisplayName("Should add a new user when valid request is provided")
    @Test
    void addNewUser() {
        when(repository.save(any())).thenReturn(entity);
        UserResponse result = service.addNewUser(request);
        assertAll("Created Entity Check",
                () -> assertEquals(ID, result.getId()),
                () -> assertEquals(USERNAME, result.getUserName()),
                () -> assertEquals(FIRSTNAME, result.getFirstName()),
                () -> assertEquals(LASTNAME, result.getLastName()),
                () -> assertEquals(EMAIL, result.getEmail()),
                () -> assertEquals(ROLE, result.getRole()));
        verify(repository, times(1)).save(any());
    }

    @Test
    void retrieveAllUsers() {
    }

    @Test
    void retrieveUserById() {
    }

    @Test
    void updateUserById() {
    }

    @Test
    void deleteUser() {
    }
}