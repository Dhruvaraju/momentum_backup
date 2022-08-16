package com.alpha.momentum.controller;

import com.alpha.momentum.enums.UserType;
import com.alpha.momentum.exception.UsersAlreadyExistsException;
import com.alpha.momentum.model.UserRequest;
import com.alpha.momentum.model.UserResponse;
import com.alpha.momentum.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserServiceImpl service;
    @InjectMocks
    private UserController controller;
    @Autowired
    private MockMvc mockMvc;
    private UserRequest request;
    private UserResponse response;

    private static final Long ID = 1L;
    private static final String USERNAME = "superman";
    private static final String FIRSTNAME = "clarke";
    private static final String LASTNAME = "kent";
    private static final String EMAIL = "clarke@dailyplanet.com";
    private static final String PASSWORD = "louis@dailyplanet";
    private static final UserType ROLE = UserType.Admin;
    private static final String USERS_BASE_URI = "/v1/users";
    private static final String USERS_BASE_URI_PATH = "/v1/users/{userId}";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(ExceptionHandler.class).build();
        request = new UserRequest(USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, ROLE);
        response = new UserResponse(ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, ROLE);
    }

    @Tag("happy-path")
    @DisplayName("01 - Should add a new user")
    @Test
    void createUser() throws Exception {
        when(service.addNewUser(any())).thenReturn(response);
        mockMvc.perform(post(USERS_BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.userName").value(USERNAME))
                .andExpect(jsonPath("$.firstName").value(FIRSTNAME))
                .andExpect(jsonPath("$.lastName").value(LASTNAME))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.role").value(ROLE.toString()));
        verify(service, times(1)).addNewUser(any());
    }

    @Tag("fail")
    @DisplayName("02 - Should not add a user and throw an exception")
    @Test
    void createUserShouldFail() throws Exception {
        doThrow(new UsersAlreadyExistsException("User exists, try with different username or email")).when(service)
                .addNewUser(any());
        mockMvc.perform(post(USERS_BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    boolean finalResult = result.getResolvedException() instanceof UsersAlreadyExistsException;
                    assertTrue(finalResult);
                });
    }

    @Test
    void fetchAllUsers() {
    }

    @Test
    void fetchUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void removeUser() {
        doThrow(new RuntimeException()).when(service).deleteUser(any());
        doNothing().when(service).deleteUser(any());
        //.andExpect(result -> {result.getResolvedException() instanceof RuntimeException});
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}