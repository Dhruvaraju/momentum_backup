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
import java.util.Optional;

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
    @DisplayName("01 - Should add a new user when valid request is provided")
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

    @Tag("happy-path")
    @Tag("read")
    @DisplayName("02 - Should fetch all users")
    @Test
    void retrieveAllUsers() {
        when(repository.findAll()).thenReturn(userEntityList);
        List<UserResponse> result = service.retrieveAllUsers();
        assertAll("Fetch all users Check",
                () -> assertEquals(ID, result.get(0).getId()),
                () -> assertEquals(USERNAME, result.get(0).getUserName()),
                () -> assertEquals(FIRSTNAME, result.get(0).getFirstName()),
                () -> assertEquals(LASTNAME, result.get(0).getLastName()),
                () -> assertEquals(EMAIL, result.get(0).getEmail()),
                () -> assertEquals(ROLE, result.get(0).getRole()));
        verify(repository, times(1)).findAll();
    }

    @Tag("happy-path")
    @Tag("read")
    @DisplayName("03 - Should fetch empty list when no users are present")
    @Test
    void retrieveAllUsersReturnsEmpty() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        List<UserResponse> result = service.retrieveAllUsers();
        assertAll("Fetch all users Check",
                () -> assertEquals(0, result.size()));
        verify(repository, times(1)).findAll();
    }

    @Tag("happy-path")
    @Tag("read")
    @DisplayName("04 - Should retrieve user based on Id")
    @Test
    void retrieveUserById() {
        when(repository.findById(ID)).thenReturn(Optional.of(entity));
        UserResponse result = service.retrieveUserById(ID);
        assertAll("User Entity By Id",
                () -> assertEquals(ID, result.getId()),
                () -> assertEquals(USERNAME, result.getUserName()),
                () -> assertEquals(FIRSTNAME, result.getFirstName()),
                () -> assertEquals(LASTNAME, result.getLastName()),
                () -> assertEquals(EMAIL, result.getEmail()),
                () -> assertEquals(ROLE, result.getRole()));
        verify(repository, times(1)).findById(ID);
    }

    @Tag("happy-path")
    @Tag("read")
    @DisplayName("05 - Should return null when user not available with provided Id")
    @Test
    void retrieveUserByIdReturnsNull() {
        when(repository.findById(ID)).thenReturn(Optional.empty());
        UserResponse result = service.retrieveUserById(ID);
        assertNull(result);
        verify(repository, times(1)).findById(ID);
    }

    @Tag("happy-path")
    @Tag("create")
    @DisplayName("06 - Should save an updated user")
    @Test
    void updateUserById() {
        when(repository.findById(ID)).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);
        UserResponse result = service.updateUserById(ID, request);
        assertAll("Created Entity Check",
                () -> assertEquals(ID, result.getId()),
                () -> assertEquals(USERNAME, result.getUserName()),
                () -> assertEquals(FIRSTNAME, result.getFirstName()),
                () -> assertEquals(LASTNAME, result.getLastName()),
                () -> assertEquals(EMAIL, result.getEmail()),
                () -> assertEquals(ROLE, result.getRole()));
        verify(repository, times(1)).save(any());
        verify(repository, times(1)).findById(ID);
    }

    @Tag("happy-path")
    @Tag("create")
    @DisplayName("07 - Should return null when trying to update an unavailable user")
    @Test
    void updateUserByIdReturnsNull() {
        when(repository.findById(ID)).thenReturn(Optional.empty());
        UserResponse result = service.updateUserById(ID, request);
        assertNull(result);
        verify(repository, times(1)).findById(ID);
    }

    @Tag("happy-path")
    @Tag("delete")
    @DisplayName("08 - Should delete a user")
    @Test
    void deleteUser() {
        service.deleteUser(ID);
        verify(repository, times(1)).deleteById(ID);
    }
}