package com.springproject.security;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springproject.core.usecase.UserUseCase;
import com.springproject.entity.mock.UserBase;
import com.springproject.util.JwtUtils;

@ExtendWith(MockitoExtension.class)
class JwtAuthFilterTest extends UserBase {
	@Mock
	public UserUseCase userUseCase;
	@Mock
	public JwtUtils jwtUtils;
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	FilterChain filterChain = mock(FilterChain.class);

	@Test
	@DisplayName("Filter with bearer token")
	void doFilterInternalWtBearerTest() throws ServletException, IOException {
		when(request.getHeader(AUTHORIZATION)).thenReturn("Bearer " + token);
		when(userUseCase.findByUsername(anyString())).thenReturn(userDomain);
		when(jwtUtils.extractUsername(anyString())).thenReturn("testUser");
		when(jwtUtils.isValidToken(anyString(), any())).thenReturn(true);

		JwtAuthFilter filter = new JwtAuthFilter(userUseCase, jwtUtils);

		filter.doFilterInternal(request, response, filterChain);

		verify(filterChain).doFilter(request, response);
	}

	@Test
	@DisplayName("Filter without bearer token")
	void doFilterInternalWOBearerTest() throws ServletException, IOException {
		JwtAuthFilter filter = new JwtAuthFilter(userUseCase, jwtUtils);
		filter.doFilterInternal(request, response, filterChain);
		verify(filterChain).doFilter(request, response);
	}

}
