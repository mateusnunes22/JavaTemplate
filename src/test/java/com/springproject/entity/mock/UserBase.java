package com.springproject.entity.mock;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.type.YesNoEnum;
import com.springproject.dataprovider.repository.entity.RoleEntity;
import com.springproject.dataprovider.repository.entity.UserEntity;
import com.springproject.dataprovider.repository.entity.UserRoleEntity;
import com.springproject.entrypoint.controller.dto.UserDto;
import com.springproject.entrypoint.controller.request.AuthenticationRequest;
import com.springproject.entrypoint.controller.request.CreateUserRequest;
import com.springproject.entrypoint.controller.response.BaseResponse;
import com.springproject.entrypoint.controller.response.UserResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserBase {
    public String token = "";

    public Date tomorrow;

    public UserDomain userDomain = new UserDomain(1L, "testUser", YesNoEnum.YES, "username", "password", new ArrayList<>(), token);

    public UserEntity userEntityBeforeSave = new UserEntity();

    public UserEntity userEntityAfterSave = new UserEntity();

    public AuthenticationRequest authenticationRequest = new AuthenticationRequest();

    public CreateUserRequest createUserRequest = new CreateUserRequest();

    public UserResponse userResponse = new UserResponse();

    public UserDto userDto = new UserDto();

    public BaseResponse baseResponse = new BaseResponse();

    public RoleEntity roleEntity = new RoleEntity();

    public UserRoleEntity userRoleEntity = new UserRoleEntity();

    public YesNoEnum isActive = YesNoEnum.YES;

    public UserBase() {

        getTomorrow();
        getJwtToken();

        userDomain.setUsername("testUser");
        userDomain.setPassword("password");
        userDomain.setName("name");
        userDomain.setIsActive(isActive);
        userDomain.setId(1L);
        userDomain.setCurrentToken(token);
        userDomain.setAuthorities(new ArrayList<>());
        userDomain.getAuthorities().add("ADMIN");

        userEntityBeforeSave.setUsername("username");
        userEntityBeforeSave.setPassword("password");
        userEntityBeforeSave.setIsActive(YesNoEnum.YES);
        userEntityBeforeSave.setId(null);
        userEntityBeforeSave.setUserRoles(List.of(new UserRoleEntity()));

        userEntityAfterSave.setUsername("testUser");
        userEntityAfterSave.setPassword("password");
        userEntityAfterSave.setIsActive(YesNoEnum.YES);
        userEntityAfterSave.setId(1L);
        userEntityAfterSave.setCurrentToken(token);
        userEntityAfterSave.setUserRoles(List.of(new UserRoleEntity()));

        authenticationRequest.setUsername("testUser");
        authenticationRequest.setPassword("password");

        createUserRequest.setUsername("testUser");
        createUserRequest.setPassword("password");

        userDto.setIsActive(YesNoEnum.YES);
        userDto.setUsername("testUser");

        userResponse.setMessage("Message");
        userResponse.setToken(token);
        userResponse.setUserDto(userDto);

        baseResponse.setMessage("Message");
        baseResponse.setToken(token);

        userRoleEntity.setId(1L);
        userRoleEntity.setUserId(1L);
        userRoleEntity.setRoleId(1L);
        userRoleEntity.setCreatedBy(1L);
        userRoleEntity.setCreatedDate(new Date());
        userRoleEntity.setLastModifiedBy(1L);
        userRoleEntity.setLastModifiedDate(new Date());

        roleEntity.setUserRole(new ArrayList<>());
        roleEntity.setId(1L);
        roleEntity.setName("ADMIN");
        roleEntity.setIsActive(YesNoEnum.YES);
    }

    void getTomorrow() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        this.tomorrow = c.getTime();
    }

    void getJwtToken() {
        this.token = Jwts.builder()
                .setSubject("testUser")
                .claim("email", "testuser@example.com")
                .claim("role", "admin")
                .claim("active", isActive.getYesNo())
                .setExpiration(tomorrow)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

}
