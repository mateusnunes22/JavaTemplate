package com.springproject.mapper;

import com.springproject.core.domain.UserDomain;
import com.springproject.entity.mock.UserBase;
import com.springproject.entrypoint.controller.response.UserResponse;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserMapperTest extends UserBase {

    @InjectMocks
    public UserMapper mapper;

    @Test
    @DisplayName("Convert to domain List")
    void toDomainListTest(){
        List<UserDomain> domainList = mapper.toDomainList(List.of(userResponse.getUserDto()));
        Assert.assertEquals(1, domainList.size());
    }

    @Test
    @DisplayName("Convert to domain List")
    void toResponseListTest(){
        List<UserResponse> responseList = mapper.toResponseList(List.of(userDomain));
        Assert.assertEquals(1, responseList.size());
    }

    @Test
    @DisplayName("Convert authorities")
    void toAuthoritiesDomainTest(){
        List<String> authorities = mapper.toAuthoritiesDomain(List.of(userRoleEntity));
        Assert.assertNotNull(authorities);
        Assert.assertEquals("ADMIN", authorities.get(0));
        Assert.assertEquals(1, authorities.size());
    }

}
