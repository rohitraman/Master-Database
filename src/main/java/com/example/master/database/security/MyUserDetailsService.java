package com.example.master.database.security;

import com.example.master.database.bean.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Value("${API_USERNAME}")
    String user;

    @Value("${API_PASSWORD}")
    String password;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (user.equals(username)) {
            return new MyUserDetails(new Login(user, password, "ROLE_ADMIN"));
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
