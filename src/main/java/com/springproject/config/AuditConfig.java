package com.springproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
	@Bean
    AuditorAwareImpl auditorProvider() {
        return new AuditorAwareImpl();
    }
}
