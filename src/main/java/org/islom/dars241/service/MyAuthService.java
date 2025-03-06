package org.islom.dars241.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("1",passwordEncoder.encode("1"), new ArrayList<>()),
                new User("hi", passwordEncoder.encode("hi123"), new ArrayList<>()),
                new User("my", passwordEncoder.encode("my123"), new ArrayList<>()),
                new User("sss", passwordEncoder.encode("sss123"), new ArrayList<>())
        ));
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
       throw new UsernameNotFoundException("User topilmadi");
    }
}
