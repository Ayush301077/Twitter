package com.example.twitter.service;


import com.example.twitter.model.User;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomeUserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

//	public CustomeUserDetailsServiceImplementation(UserRepository userRepository) {
//		this.userRepository=userRepository;
//	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if(user == null || user.isLogin_with_google()) {
            throw new UsernameNotFoundException("user not found with email "+username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

    }


}
