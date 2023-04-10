package com.springproject.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.springproject.core.domain.UserDomain;
import com.springproject.util.JwtUtils;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        UserDomain userDomain = JwtUtils.userLogged();
        if (userDomain != null)
            return Optional.of(userDomain.getId());
        else
            return Optional.empty();
    }

}
