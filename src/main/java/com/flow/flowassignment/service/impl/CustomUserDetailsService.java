package com.flow.flowassignment.service.impl;

import com.flow.flowassignment.model.USER;
import com.flow.flowassignment.service.ExtensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ExtensionMapper extensionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String user_id = username;
        return extensionMapper.findByUserId(user_id)
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UsernameNotFoundException(user_id + "> 찾을 수 없습니다."));
    }

    private USER addAuthorities(USER user) {
        user.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(user.getUser_role())));

        return user;
    }
}