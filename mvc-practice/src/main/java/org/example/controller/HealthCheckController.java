package org.example.controller;

import org.example.annotation.Controller;
import org.example.annotation.ReqeustMethod;
import org.example.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HealthCheckController {

    @RequestMapping(value = "/health", method = ReqeustMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response){

        // 메소드는 사실 필요 없다. 이유는 Controller 어노테이션이 붙은 클래스만 찾는 것이기 때문에 필요 없지만..
        return "ok";
    }
}
