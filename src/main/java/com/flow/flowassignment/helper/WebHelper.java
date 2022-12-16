package com.flow.flowassignment.helper;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpRequest;
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
    private HttpSession session;

    public void init(HttpServletResponse response){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        this.request = requestAttributes.getRequest();
        this.response = response;
        this.session = request.getSession();

        try {
            // 인코딩 설정하기
            this.request.setCharacterEncoding("utf-8");
            this.response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String fieldName, String defaultValue) {
        // 리턴을 위한 값을 두 번째 파라미터(기본값)로 설정해 둔다.
        String result = defaultValue;
        // GET,POST 파라미터를 받는다.
        String param = this.request.getParameter(fieldName);

        if (param != null) { // 값이 null이 아니라면?
            param = param.trim(); // 앞뒤 공백을 제거한다.
            if (!param.equals("")) { // 공백제거 결과가 빈 문자열이 아니라면?
                result = param; // 리턴을 위해서 준비한 변수에 수신한 값을 복사한다.
            }
        }

        // 값을 리턴. param값이 존재하지 않을 경우 미리 준비한 기본값이 그대로 리턴된다.
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
