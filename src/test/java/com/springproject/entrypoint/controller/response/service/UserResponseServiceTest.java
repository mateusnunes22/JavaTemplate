package com.springproject.entrypoint.controller.response.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springproject.entity.mock.UserBase;
import com.springproject.entrypoint.controller.dto.UserDto;
import com.springproject.entrypoint.controller.response.UserResponse;
import com.springproject.entrypoint.controller.response.service.impl.UserResponseServiceImpl;
import com.springproject.mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
class UserResponseServiceTest extends UserBase {

    @InjectMocks
    public UserResponseServiceImpl userResponse;

    @Mock
    public UserMapper mapper;

    @Test
    @DisplayName("Create user")
    void createUserTest(){
        when(mapper.map(userDomain, UserDto.class)).thenReturn(userDto);
        UserResponse response = userResponse.createUser(userDomain);
        assertNotNull(response.getUserDto());
    }

}
