package com.springproject.dataprovider;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.messages.UserMsg;
import com.springproject.dataprovider.impl.UserDataProviderImpl;
import com.springproject.dataprovider.repository.UserRepository;
import com.springproject.dataprovider.repository.UserRoleRepository;
import com.springproject.dataprovider.repository.entity.UserEntity;
import com.springproject.dataprovider.repository.entity.UserRoleEntity;
import com.springproject.entity.mock.UserBase;
import com.springproject.mapper.UserMapper;
import com.springproject.util.JwtUtils;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDataProviderTest extends UserBase {

    @InjectMocks
    public UserDataProviderImpl userDataProviderImpl;

    @Mock
    public UserRepository userRepository;

    @Mock
    public UserRoleRepository userRoleRepository;

    @Mock
    public JwtUtils jwtUtils;

    @Mock
    public UserMapper mapper;

    @Test
    @DisplayName(value = "Find User by username")
    void findByUsernameTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userEntityAfterSave));
        when(mapper.map(userEntityAfterSave, UserDomain.class)).thenReturn(userDomain);
        when(mapper.toAuthoritiesDomain(userEntityAfterSave.getUserRoles())).thenReturn(List.of("LEVVA_ROLE"));
        UserDomain userDomain = userDataProviderImpl.findByUsername("username");
        assertNotNull(userDomain);
    }

    @Test
    @DisplayName(value = "Throw User by username")
    void findByUsernameThrowTest() {
        when(userRepository.findByUsername(anyString())).thenThrow(new EntityNotFoundException(UserMsg.ERROR_USER_NOT_FOUND));
        assertThrows(UsernameNotFoundException.class, () -> userDataProviderImpl.findByUsername("username"));
    }

    @Test
    @DisplayName(value = "Create user: Success")
    void createUserSuccessTest() {
        when(mapper.map(this.userDomain, UserEntity.class)).thenReturn(this.userEntityBeforeSave);
        when(userRepository.save(any())).thenReturn(this.userEntityAfterSave);
        when(userRoleRepository.save(any())).thenReturn(new UserRoleEntity());
        when(mapper.map(this.userEntityAfterSave, UserDomain.class)).thenReturn(this.userDomain);
        UserDomain userDomain = userDataProviderImpl.createUser(this.userDomain);
        assertNotNull(userDomain);
    }

    @Test
    @DisplayName(value = "Create user: Exception")
    void createUserExceptionTest() {
        when(mapper.map(this.userDomain, UserEntity.class)).thenReturn(this.userEntityBeforeSave);
        when(userRepository.save(any())).thenThrow(new HibernateException(UserMsg.ERROR_SAVE_USER));
        assertThrows(HibernateException.class, () -> userDataProviderImpl.createUser(this.userDomain));
    }

    @Test
    @DisplayName(value = "Create UserRole: Exception")
    void createUserRoleExceptionTest() {
        when(mapper.map(this.userDomain, UserEntity.class)).thenReturn(this.userEntityBeforeSave);
        when(userRepository.save(any())).thenReturn(this.userEntityAfterSave);
        when(userRoleRepository.save(any())).thenThrow(new HibernateException(UserMsg.ERROR_SAVE_USERID_IN_USER_ROLE));
        assertThrows(HibernateException.class, () -> userDataProviderImpl.createUser(this.userDomain));
    }

    @Test
    @DisplayName(value = "Authenticate user")
    void authenticateTest() {
        this.findByUsernameTest();
        when(jwtUtils.generateToken(any())).thenReturn("token_jwt");
        UserDomain userDomain = userDataProviderImpl.authenticate("username");
        Assert.notNull(userDomain);
    }

    @Test
    @DisplayName(value = "Throw Authenticate user")
    void authenticateThrowTest() {
        when(userRepository.findByUsername(anyString())).thenThrow(new EntityNotFoundException("username or password have been wrong!"));
        assertThrows(EntityNotFoundException.class, () -> userDataProviderImpl.authenticate("username"));
    }

    @Test
    @DisplayName(value = "Throw Refresh Token")
    void refreshTokenThrowTest() {
        when(userRepository.save(any())).thenThrow(new SecurityException(UserMsg.ERROR_REFRESH_TOKEN));
        assertThrows(SecurityException.class, () -> userDataProviderImpl.refreshToken(userDomain));
    }

    @Test
    @DisplayName(value = "Invalidate Session")
    void invalidateSessionTest() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);
        when(authentication.getPrincipal()).thenReturn(userDomain);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(mapper.map(userDomain, UserEntity.class)).thenReturn(this.userEntityAfterSave);
        userDataProviderImpl.invalidateSession();
        verify(userRepository, times(1)).save(userEntityAfterSave);
        resetMocks(securityContext, authentication);
    }

    @Test
    @DisplayName(value = "Invalidate Session: Exception")
    void invalidateSessionExceptionTest() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);
        when(authentication.getPrincipal()).thenThrow(new SecurityException(UserMsg.ERROR_INVALIDATE_SESSION));
        assertThrows(SecurityException.class, () -> userDataProviderImpl.invalidateSession());
        resetMocks(securityContext, authentication);
    }

    private void resetMocks(SecurityContext securityContext, Authentication authentication) {
        reset(securityContext);
        reset(authentication);
    }


}
