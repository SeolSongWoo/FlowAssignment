package com.flow.flowassignment.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.List;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    public UserAuthentication(String principal, String credentials,
                              List<GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}