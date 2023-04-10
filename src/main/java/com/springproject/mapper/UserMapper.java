package com.springproject.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.type.RoleEnum;
import com.springproject.dataprovider.repository.entity.UserRoleEntity;
import com.springproject.entrypoint.controller.response.UserResponse;

@Component
public class UserMapper extends ModelMapper {

	public List<UserDomain> toDomainList(List<?> fromList) {
		return fromList.stream().map(element -> map(element, UserDomain.class)).toList();
	}

	public List<UserResponse> toResponseList(List<?> fromList) {
		return fromList.stream().map(element -> map(element, UserResponse.class)).toList();
	}

	public List<String> toAuthoritiesDomain(List<UserRoleEntity> userRoles) {
		List<String> domainAuthorities = new ArrayList<>();
		if (userRoles != null)
			for (UserRoleEntity userRoleEntity : userRoles) {
				for (RoleEnum e : RoleEnum.values()) {
					if (e.getId().equals(userRoleEntity.getRoleId())) {
						domainAuthorities.add(e.getRole());
					}
				}
			}
		return domainAuthorities;
	}
}
