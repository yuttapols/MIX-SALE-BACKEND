package com.enterprise.mixsale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enterprise.mixsale.repository.AuthenticationRepository;
import com.enterprise.mixsale.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService{

	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {

                return authenticationRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
