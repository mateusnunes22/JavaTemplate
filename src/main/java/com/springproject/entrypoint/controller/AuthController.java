package com.springproject.entrypoint.controller;

import com.springproject.dao.UserDao;
import com.springproject.entrypoint.controller.request.AuthenticationRequest;
import com.springproject.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails user = userDao.findUserByEmail(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred!");
    }
}
