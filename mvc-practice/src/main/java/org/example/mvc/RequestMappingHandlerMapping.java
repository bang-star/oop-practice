package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;
import org.example.mvc.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    // [Key] URL Path, [Value] Controller
    private Map<String, Controller> mappings = new HashMap<>();

    // 인덱스 페이지에 대한 설정
    void init(){
        mappings.put("/", new HomeController());
        mappings.put("/users", new UserListController());
    }

    // URI 경로에 따른 컨트롤러 호출 메소드
    public Controller findHanlder(String uriPath){
        return mappings.get(uriPath);
    }
}
