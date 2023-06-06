package com.springproject.security;

import com.springproject.core.usecase.UserUseCase;
import com.springproject.entity.mock.UserBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest extends UserBase {

    @InjectMocks
    public SecurityConfig securityConfig;

    @Mock
    public JwtAuthFilter jwtAuthFilter;

    @Mock
    public UserUseCase userUseCase;

    @Test
    void userDetailsServiceTest() throws Exception {
        when(userUseCase.findByUsername(Mockito.anyString())).thenReturn(userDomain);
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        assertEquals("testUser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
    }

}
