package com.springproject.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.springproject.entity.mock.UserBase;

@ExtendWith(MockitoExtension.class)
class AuditorAwareTest extends UserBase {
	
	@InjectMocks
	private AuditorAwareImpl auditorAware;

	SecurityContext securityContext = mock(SecurityContext.class);

	Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);

	
	@Test
	void testGetCurrentAuditorWhenUserIsLogged() {
		mockUserLoggedOrNot(true);
		Optional<Long> result = auditorAware.getCurrentAuditor();
		assertTrue(result.isPresent());
		assertEquals(1L, result.get().longValue());
		reset(securityContext);
        reset(authentication);
	}

	@Test
	void testGetCurrentAuditorWhenUserIsNotLogged() {
		mockUserLoggedOrNot(false);
		Optional<Long> result = auditorAware.getCurrentAuditor();
		assertFalse(result.isPresent());
	}

	void mockUserLoggedOrNot(boolean isLogged) {
		when(authentication.isAuthenticated()).thenReturn(isLogged);
		if (isLogged)
			when(authentication.getPrincipal()).thenReturn(userDomain);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

}
