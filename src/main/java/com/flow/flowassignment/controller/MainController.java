package com.flow.flowassignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flow.flowassignment.helper.WebHelper;
import com.flow.flowassignment.model.EXTENSION;
import com.flow.flowassignment.service.ExtensionMapper;
import com.flow.flowassignment.service.impl.ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

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
    public void ExtensionPageADD(HttpServletResponse response) {
        web.init(response);

        HashMap<String,Object> data = new HashMap<>();

        String Value = web.getString("Value",null);
        int OverLabCheck = 0;
        if(Value == null || Value == "") { web.printJsonRt("Null"); return;}
        if(Value.length() > 20) { web.printJsonRt("최대 입력 길이는 20글자입니다."); return;};
        try {
            OverLabCheck = exService.ExOverLabCheck(Value);
        }catch (Exception e) {
            web.printJsonRt(e.getMessage());
            return;
        }

        if(OverLabCheck == 0) {
            try {
                exService.ExtensionAdd(Value);
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

    @Transactional
    @PostMapping("/extensiondel")
    public void ExtensionPageDEL(HttpServletResponse response) {
        web.init(response);
        HashMap<String,Object> data = new HashMap<>();

        String Value = web.getString("Value",null);

        if(Value == null || Value == "") { web.printJsonRt("Null"); return;}
        if(Value.length() > 20) { web.printJsonRt("최대 입력 길이는 20글자입니다."); return;};

        List<EXTENSION> ExList = null;
        try {
            exService.ExtensionDel(Value);
            ExList = exService.ExtensionList();
        }catch(Exception e) {
            web.printJsonRt(e.getMessage());
            return;
        }

        data.put("rt","ok");
        data.put("ExList",ExList);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getWriter(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
