package com.springproject.utils;

import com.springproject.entity.mock.UserBase;
import com.springproject.util.JwtUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class JwtUtilsTest extends UserBase {

    @InjectMocks
    public JwtUtils jwtUtils;

    @Test
    void extractUsernameTest() {
        String username = jwtUtils.extractUsername(token);
        assertEquals("testUser", username);
    }

    @Test
    void extractExpirationTest() {
        Date date = jwtUtils.extractExpiration(token);
        Assert.assertEquals(date.getDate(), tomorrow.getDate());
        Assert.assertEquals(date.getMonth(), tomorrow.getMonth());
        Assert.assertEquals(date.getYear(), tomorrow.getYear());
    }

    @Test
    void hasClaimTrueTest() {
        boolean hasClaim = jwtUtils.hasClaim(token, "email");
        Assert.assertTrue(hasClaim);
    }

    @Test
    void hasClaimFalseTest() {
        boolean hasClaim = jwtUtils.hasClaim(token, "test");
        Assert.assertFalse(hasClaim);
    }

    @Test
    void generateTokenTest() {
        String token = jwtUtils.generateToken(this.userDomain);
        Assert.assertNotNull(token);
    }

    @Test
    void generateTokenWithClaimsTest() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", "testuser@example.com");
        String token = jwtUtils.generateToken(this.userDomain, claims);
        Assert.assertNotNull(token);
    }

    @Test
    void isValidTokenTest() {
        Boolean isValid = jwtUtils.isValidToken(this.token, this.userDomain);
        Assert.assertTrue(isValid);
    }

    @Test
    void isValidTokenThrowTest() {
        Assert.assertThrows(SecurityException.class, () -> jwtUtils.isValidToken("Invalid token", this.userDomain));
    }

}
