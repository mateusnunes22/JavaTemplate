package com.springproject.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InvalidGenericExceptionTest {
	
	@Test
	@DisplayName("Exception with message")
	void exceptionWithMessage() {
		InvalidGenericException invalidGenericException = new InvalidGenericException("Message");
		assertEquals("Message", invalidGenericException.getMessage());
	}

	@Test
	@DisplayName("Exception with message and cause")
	void exceptionWithMessageAndCause() {
		assertNotNull(new InvalidGenericException("Message", new Throwable("Cause")));
	}
}
