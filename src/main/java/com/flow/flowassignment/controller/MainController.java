package com.flow.flowassignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flow.flowassignment.helper.WebHelper;
import com.flow.flowassignment.model.EXTENSION;
import com.flow.flowassignment.service.impl.ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public String GotoMain() { return "redirect:/main";}

    @GetMapping("/main")
    public ModelAndView MainPage(HttpServletResponse response, Model model) {
        web.init(response);
        List<EXTENSION> ExBasicList = null;
        List<EXTENSION> ExCustomList = null;
        int ExCount = 0;
        try {
            ExBasicList = exService.ExtensionList("BASIC");
            ExCustomList = exService.ExtensionList("CUSTOM");
            ExCount = exService.ExtensionCount("CUSTOM");
        }catch(Exception e) {
            web.printJsonRt(e.getMessage());
        }

        model.addAttribute("ExBasicList",ExBasicList.toString());
        model.addAttribute("ExCustomList",ExCustomList);
        model.addAttribute("excount",ExCount);
        return new ModelAndView("main");
    }

    @Transactional
    @PostMapping("/extensionadd")
    public void ExtensionPageADD(HttpServletResponse response) {
        web.init(response);


        String Name = web.getString("Name",null);
        String Sort = web.getString("Sort",null);

        EXTENSION extension = new EXTENSION();
        int OverLabCheck = 0;

        if(Name == null || Name == "") { web.printJsonRt("Null"); return;}
        if(!(Sort.equals("BASIC") || Sort.equals("CUSTOM"))) { web.printJsonRt("정상적인 경로로 접근해주세요."); return; }
        if(Name.length() > 20) { web.printJsonRt("최대 입력 길이는 20글자입니다."); return;}

        extension.setEx_name(Name);
        extension.setEx_sort(Sort);

        try {
            OverLabCheck = exService.ExOverLabCheck(Name);
        }catch (Exception e) {
            web.printJsonRt(e.getMessage());
            return;
        }

        if(OverLabCheck == 0) {
            try {
                exService.ExtensionAdd(extension);
            }catch(Exception e) {
                web.printJsonRt(e.getMessage());
                return;
            }
        }

        web.printJsonRt("ok");
    }

    @Transactional
    @DeleteMapping("/extensiondel")
    public void ExtensionPageDEL(HttpServletResponse response) {
        web.init(response);

        String Name = web.getString("Name",null);

        if(Name == null || Name == "") { web.printJsonRt("Null"); return;}
        if(Name.length() > 20) { web.printJsonRt("최대 입력 길이는 20글자입니다."); return;};

        try {
            exService.ExtensionDel(Name);
        }catch(Exception e) {
            web.printJsonRt(e.getMessage());
            return;
        }

        web.printJsonRt("ok");
    }
}
