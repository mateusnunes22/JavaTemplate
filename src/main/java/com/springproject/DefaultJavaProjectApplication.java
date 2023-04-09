package com.springproject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class DefaultJavaProjectApplication {

	@Value("${project.version}")
	private String apiVersion;

	@Value("${project.description}")
	private String apiTitle;

	@Value("${server.servlet.contextPath}")
	private String apiPath;

	public static void main(String[] args) {
		SpringApplication.run(DefaultJavaProjectApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title(apiTitle).version(apiVersion))
				.addServersItem(new Server().url(apiPath));
	}

}
