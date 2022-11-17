package org.example.mvc.controller;

import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Users 정보 전달

        request.setAttribute("users", UserRepository.findAll());

        return "/user/list";
    }
}
