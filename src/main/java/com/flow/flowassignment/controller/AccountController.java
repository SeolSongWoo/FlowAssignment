package com.flow.flowassignment.controller;

import com.flow.flowassignment.helper.WebHelper;
import com.flow.flowassignment.jwt.JwtTokenProvider;
import com.flow.flowassignment.model.USER;
import com.flow.flowassignment.service.impl.ExService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountController {

    @Autowired
    WebHelper web;

    @Autowired
    ExService exService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

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
        try {
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtTokenProvider.createToken(user.getUser_id(), "ADMIN");
                response.setHeader("Authorization", "Bearer " + token);
                Cookie cookie = new Cookie("Authorization", URLEncoder.encode("Bearer " + token, "utf-8"));
                cookie.setMaxAge(-1);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                web.printJsonRt("ok");
            } else {
                web.printJsonRt("비밀번호를 제대로 입력해주세요.");
            }
        } catch(Exception e) {
            web.printJsonRt(e.toString());
        }
    }
}
