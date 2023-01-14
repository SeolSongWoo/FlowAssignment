package com.flow.flowassignment.controller;

import com.flow.flowassignment.helper.WebHelper;
import com.flow.flowassignment.jwt.JwtTokenProvider;
import com.flow.flowassignment.model.USER;
import com.flow.flowassignment.service.impl.ExService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final WebHelper web;

    private final ExService exService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.token-validity-in-seconds}")
    private int cookie_maxtime_ms;

    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/")
    public String GotoMain() { return "redirect:/login";}

    @PostMapping("/login/check")
    public void LoginCheck(HttpServletResponse response) throws UnsupportedEncodingException {
        web.init(response);

        String id = web.getString("id",null);
        String password = web.getString("password",null);


        if(id == null || password == null) {web.printJsonRt("정상적인 경로로 접근해주세요.");}
        USER user = null;

        try {
            user = exService.findByUserId(id).get();
        }catch (Exception e) {
            web.printJsonRt(e.getMessage());
            return;
        }
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtTokenProvider.createToken(user.getUser_id(), "ADMIN");
                Cookie cookie = new Cookie("Authorization", URLEncoder.encode("Bearer " + token, "utf-8"));
                cookie.setMaxAge(cookie_maxtime_ms/1000);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                web.printJsonRt("ok");
            } else {
                web.printJsonRt("비밀번호를 제대로 입력해주세요.");
            }
    }
}
