package com.springproject.dataprovider.impl;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.domain.dataprovider.UserDataProvider;
import com.springproject.core.type.YesNoEnum;
import com.springproject.dataprovider.repository.UserRepository;
import com.springproject.dataprovider.repository.UserRoleRepository;
import com.springproject.dataprovider.repository.entity.UserEntity;
import com.springproject.dataprovider.repository.entity.UserRoleEntity;
import com.springproject.mapper.UserMapper;
import com.springproject.util.JwtUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class UserDataProviderImpl implements UserDataProvider {

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final UserMapper mapper;
	private final JwtUtils jwtUtils;

	@Override
	public UserDomain findByUsername(String username) {
		UserEntity entity = userRepository.findByUsername(username).stream().findFirst()
				.orElseThrow(() -> new UsernameNotFoundException("No user was found!"));
		UserDomain userDomain = mapper.map(entity, UserDomain.class);
		userDomain.getAuthorities().addAll(mapper.toAuthoritiesDomain(entity.getUserRoles()));
		return userDomain;
	}

	@Override
	public UserDomain createUser(UserDomain userDomain) {
		UserEntity userEntity = mapper.map(userDomain, UserEntity.class);
		userEntity.setIsActive(YesNoEnum.YES);
		userEntity = userRepository.save(userEntity);
		setRoleCreateAccount(userEntity.getId());
		return mapper.map(userEntity, UserDomain.class);
	}

	private void setRoleCreateAccount(Long userId) {
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleEntity.setUserId(userId);
		userRoleEntity.setRoleId(1L);
		userRoleRepository.save(userRoleEntity);
	}

	@Override
	public UserDomain authenticate(String username) {
		UserDomain userDomain = findByUsername(username);
		this.refreshToken(userDomain);
		return userDomain;
	}

	@Override
	public UserDomain refreshToken(UserDomain userDomain) {
		userDomain.setCurrentToken(jwtUtils.generateToken(userDomain));
		UserEntity entity = userRepository.save(mapper.map(userDomain, UserEntity.class));
		return mapper.map(entity, UserDomain.class);
	}

	@Override
	public String invalidateSession() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDomain userDomain = (UserDomain) authentication.getPrincipal();
		authentication.setAuthenticated(false);
		userDomain.setCurrentToken(null);
		userRepository.save(mapper.map(userDomain, UserEntity.class));
		return "Session encerred";
	}

}
