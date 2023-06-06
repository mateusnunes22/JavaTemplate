package com.springproject.entrypoint.controller;

import com.springproject.core.usecase.UserUseCase;
import com.springproject.entity.mock.UserBase;
import com.springproject.entrypoint.controller.response.service.BaseResponseService;
import com.springproject.entrypoint.controller.response.service.UserResponseService;
import com.springproject.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest extends UserBase {

    @InjectMocks
    public AuthController authController;

    @Mock
    public UserUseCase userUseCase;

    @Mock
    public UserResponseService userResponseService;

    @Mock
    public UserMapper mapper;

    @Mock
    public BaseResponseService baseResponseService;

    @Mock
    public AuthenticationManager authenticationManager;


    @Test
    @DisplayName("Authenticate user: Status OK")
    void authenticateSuccessTest() {
        assertNotNull(authController.authenticate(authenticationRequest));
    }

    @Test
    @DisplayName("Authenticate user: Exception")
    void authenticateThrowTest() {
        when(authenticationManager.authenticate(any())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
        assertThrows(ResponseStatusException.class, () -> authController.authenticate(authenticationRequest));
    }

    @Test
    @DisplayName("Create user: Status OK")
    void createUserSuccessTest() {
        assertNotNull(authController.createUser(createUserRequest));
    }

    @Test
    @DisplayName("Create user: Exception")
    void createUserThrowTest() {
        when(userResponseService.createUser(userDomain)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
        assertThrows(ResponseStatusException.class, () -> authController.createUser(createUserRequest));
    }

    @Test
    @DisplayName("Invalidate session: Status OK")
    void invalidateSessionSuccessTest() {
        String textReturn = "Session Closed";
        ResponseEntity<Object> result = authController.invalidateSession();
        assertEquals(result.getBody(), null);
    }

//    @Test
//    @DisplayName("Invalidate session: Exception")
//    void invalidateSessionThrowTest() {
//        when(userUseCase.invalidateSession()).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
//        assertThrows(ResponseStatusException.class, () -> authController.invalidateSession());
//    }


}
