package com.springproject.core.usecase;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.domain.dataprovider.UserDataProvider;
import com.springproject.core.usecase.impl.UserUseCaseImpl;
import com.springproject.entity.mock.UserBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest extends UserBase {

    @InjectMocks
    public UserUseCaseImpl userUseCase;

    @Mock
    public UserDataProvider userDataProvider;

    @Test
    @DisplayName("Find by username")
    void findByUsernameTest() {
        when(userDataProvider.findByUsername(any())).thenReturn(this.userDomain);
        UserDomain aa = userUseCase.findByUsername("username");
        assertEquals(aa, this.userDomain);
    }

    @Test
    @DisplayName("Authenticate")
    void authenticateTest() {
        when(userDataProvider.authenticate(any())).thenReturn(this.userDomain);
        String result = userUseCase.authenticate("username");
        assertEquals(result, this.userDomain.getCurrentToken());
    }

    @Test
    @DisplayName("Authenticate without token")
    void authenticateWOTokenTest() {
        when(userDataProvider.authenticate(any())).thenReturn(new UserDomain());
        assertThrows(SecurityException.class, () -> userUseCase.authenticate("username"));
    }

    @Test
    @DisplayName("Refresh token")
    void refreshTokenTest() {
        when(userDataProvider.refreshToken(any())).thenReturn(this.userDomain);
        UserDomain userDomain = userUseCase.refreshToken(this.userDomain);
        assertNotNull(userDomain);
    }

    @Test
    @DisplayName("Create user")
    void createUserTest() {
        when(userDataProvider.createUser(any())).thenReturn(this.userDomain);
        UserDomain userDomain = userUseCase.createUser(this.userDomain);
        assertNotNull(userDomain);
    }

    @Test
    @DisplayName("Invalidate Session")
    void invalidateSessionTest() {
        userUseCase.invalidateSession();
        verify(userDataProvider, times(1)).invalidateSession();
    }


}
