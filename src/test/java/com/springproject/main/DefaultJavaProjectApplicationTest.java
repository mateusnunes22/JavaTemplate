package com.springproject.main;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.springproject.DefaultJavaProjectApplication;

@ExtendWith(MockitoExtension.class)
class DefaultJavaProjectApplicationTest {
	
	@Spy
	public DefaultJavaProjectApplication defaultJavaProjectApplication = new DefaultJavaProjectApplication();
	
	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(defaultJavaProjectApplication, "apiVersion", "@project.version@");
		ReflectionTestUtils.setField(defaultJavaProjectApplication, "apiTitle", "@project.description@");
		ReflectionTestUtils.setField(defaultJavaProjectApplication, "apiPath", "/api/default");
	}
	
	@Test
	@DisplayName("Run application and Swagger test")
	void customOpenAPITest() {
		String [] args = {""};
		DefaultJavaProjectApplication.main(args);
		assertNotNull(defaultJavaProjectApplication.customOpenAPI());
	}
	
}
