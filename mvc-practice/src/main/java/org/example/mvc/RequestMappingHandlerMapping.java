package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {

    // [Key] URL Path, [Value] Controller
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    // 인덱스 페이지에 대한 설정
    void init(){
//        mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    // URI 경로에 따른 컨트롤러 호출 메소드
    public Controller findHanlder(HandlerKey handlerKey){
        return mappings.get(handlerKey);
    }
}
