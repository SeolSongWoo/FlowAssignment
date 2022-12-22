package com.flow.flowassignment.service;

import com.flow.flowassignment.model.USER;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
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
        return extensionMapper.findByUserId(username)
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UsernameNotFoundException(username + "> 찾을 수 없습니다."));
    }

    private USER addAuthorities(USER user) {
        user.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

        return user;
    }
}