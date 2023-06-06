package com.springproject.entrypoint.controller.response.service;

import com.springproject.core.usecase.UserUseCase;
import com.springproject.entity.mock.UserBase;
import com.springproject.entrypoint.controller.response.BaseResponse;
import com.springproject.entrypoint.controller.response.service.impl.BaseResponseServiceImpl;
import com.springproject.util.JwtUtils;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaseResponseServiceTest extends UserBase {

    @InjectMocks
    public BaseResponseServiceImpl baseResponseService;

    @Mock
    public UserUseCase userUseCase;

    @Test
    @DisplayName("Add response without message")
    void addBaseResponseTest(){
        when(userUseCase.refreshToken(any())).thenReturn(userDomain);
        BaseResponse result = baseResponseService.addBaseResponse("Message");
        Assert.assertNotNull(result);
    }
}
