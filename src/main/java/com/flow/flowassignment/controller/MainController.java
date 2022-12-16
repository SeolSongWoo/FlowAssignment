package com.flow.flowassignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flow.flowassignment.helper.WebHelper;
import com.flow.flowassignment.service.ExtensionMapper;
import com.flow.flowassignment.service.impl.ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class MainController {

    @Autowired
    WebHelper web;

    @Autowired
    ExService exService;

    @GetMapping("/")
    public ModelAndView MainPage(HttpServletResponse response) {

        return new ModelAndView("main");
    }


    @PostMapping("/extensionadd")
    public void ExtensionPage(HttpServletResponse response) {
        web.init(response);

        HashMap<String,String> data = new HashMap<>();

        String BasicValue = web.getString("BasicValue",null);
        int OverLabCheck = 0;
        if(BasicValue == null || BasicValue == "") { web.printJsonRt("Null");}
        try {
            OverLabCheck = exService.ExOverLabCheck(BasicValue);
        }catch (Exception e) {
            web.printJsonRt(e.getMessage());
            return;
        }

        if(OverLabCheck == 0) {
            try {
                exService.ExtensionAdd(BasicValue);
            }catch(Exception e) {
                web.printJsonRt(e.getMessage());
                return;
            }
        }
        data.put("rt","ok");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getWriter(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
