package com.springproject.dataprovider.impl;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.domain.dataprovider.UserDataProvider;
import com.springproject.core.type.YesNoEnum;
import com.springproject.dataprovider.repository.UserRepository;
import com.springproject.dataprovider.repository.UserRoleRepository;
import com.springproject.dataprovider.repository.entity.UserEntity;
import com.springproject.dataprovider.repository.entity.UserRoleEntity;
import com.springproject.exception.GlobalExceptionHandler;
import com.springproject.mapper.UserMapper;
import com.springproject.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.springproject.core.messages.UserMsg.*;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserDataProviderImpl implements UserDataProvider {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper mapper;
    private final JwtUtils jwtUtils;

    @Override
    public UserDomain findByUsername(String username) {
        try {
            log.info("Data provider: Starting to find entity by username");
            UserEntity entity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ERROR_USER_NOT_FOUND));
            UserDomain userDomain = mapper.map(entity, UserDomain.class);
            userDomain.getAuthorities().addAll(mapper.toAuthoritiesDomain(entity.getUserRoles()));
            return userDomain;
        } catch (Exception e) {
            return GlobalExceptionHandler.usernameExceptionAndThrow(e, ERROR_USER_NOT_FOUND, username);
        }
    }

    @Override
    public UserDomain createUser(UserDomain userDomain) {
        try {
            log.info("Data provider: Starting the process to create a new user");
            UserEntity userEntity = mapper.map(userDomain, UserEntity.class);
            userEntity.setIsActive(YesNoEnum.YES);
            userEntity = userRepository.save(userEntity);
            setRoleCreateAccount(userEntity.getId());
            return mapper.map(userEntity, UserDomain.class);
        } catch (Exception e) {
            return GlobalExceptionHandler.hibernateExceptionAndThrow(e, ERROR_SAVE_USER, userDomain);
        }
    }

    private void setRoleCreateAccount(Long userId) {
        try {
            log.info("Data provider: Starting the process to join User and Role.");
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(1L);
            userRoleRepository.save(userRoleEntity);
        } catch (Exception e) {
            GlobalExceptionHandler.hibernateExceptionAndThrow(e, ERROR_SAVE_USERID_IN_USER_ROLE, userId);
        }
    }

    @Override
    public UserDomain authenticate(String username) {
        try {
            log.info("Data provider: Starting the process to find the user by the username");
            UserDomain userDomain = findByUsername(username);
            this.refreshToken(userDomain);
            return userDomain;
        } catch (Exception e) {
            return GlobalExceptionHandler.entityExceptionAndThrow(e, ERROR_USERNAME_PASSWORD, username);
        }
    }

    @Override
    public UserDomain refreshToken(UserDomain userDomain) {
        try {
            log.info("Data provider: Starting the process to get a new token");
            userDomain.setCurrentToken(jwtUtils.generateToken(userDomain));
            UserEntity entity = userRepository.save(mapper.map(userDomain, UserEntity.class));
            return mapper.map(entity, UserDomain.class);
        } catch (Exception e) {
            return GlobalExceptionHandler.securityExceptionAndThrow(e, ERROR_REFRESH_TOKEN, userDomain);
        }
    }

    @Override
    public void invalidateSession() {
        log.info("Data provider: Getting the logged user to start the process to invalidate the session");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            UserDomain userDomain = (UserDomain) authentication.getPrincipal();
            authentication.setAuthenticated(false);
            userDomain.setCurrentToken(null);
            userRepository.save(mapper.map(userDomain, UserEntity.class));
        } catch (Exception e) {
            GlobalExceptionHandler.securityExceptionAndThrow(e, ERROR_INVALIDATE_SESSION, authentication);
        }
    }

}
