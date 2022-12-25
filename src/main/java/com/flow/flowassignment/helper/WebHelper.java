package com.flow.flowassignment.helper;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebHelper {


    private HttpServletResponse response;
    private HttpServletRequest request;

    public void init(HttpServletResponse response){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        this.request = requestAttributes.getRequest();
        this.response = response;

        try {
            // 인코딩 설정하기
            this.request.setCharacterEncoding("utf-8");
            this.response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String fieldName, String defaultValue) {
        String result = defaultValue;
        String param = this.request.getParameter(fieldName);

        if (param != null) {
            param = param.trim();
            if (!param.equals("")) {
                result = param;
            }
        }

        return result;
    }

    public void printJsonRt(String rt) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("rt", rt);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getWriter(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
