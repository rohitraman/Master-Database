package com.example.master.database.controller;

import com.example.master.database.bean.Login;
import com.example.master.database.bean.Response;
import com.example.master.database.security.MyUserDetailsService;
import com.example.master.database.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    TokenManager tokenManager;

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody Login login) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUsername(), login.getPassword()));
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(login.getUsername());
        return new ResponseEntity<>(new Response(tokenManager.generateToken(userDetails), 200), HttpStatus.OK);
    }

}