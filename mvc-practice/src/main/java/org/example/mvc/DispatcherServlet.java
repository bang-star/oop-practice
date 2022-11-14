package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JSPViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private HandlerMapping handlerMapping;          // 구체적인 클래스 타입이 아닌 인터페이스 타입으로 선언

    private List<HandlerAdapter> handlerAdapters;

    private List<ViewResolver> viewResolvers;

    @Override
    public void init() throws ServletException {
        RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
        rmhm.init();

        handlerMapping = rmhm;

        handlerAdapters = List.of(new SimpleControllerHandlaerAdapter());
        viewResolvers = Collections.singletonList(new JSPViewResolver());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 모든 경로에 대해 호출되는지 판단하기 위한 로그
        log.info("[DispatcherServlet] service started");

        try {
            // Handler Mapping
            Object handler = handlerMapping.findHanlder(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI()));     // 1. 요청된 경로를 통해 적절한 컨트롤러 반환

            // String viewName = handler.handleRequest(request, response);         // 2. Handler에게 작업을 위임하고 해당 viewName을 받는다.

            // AS-IS : forward만 가능한 코드
            // RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
            // requestDispatcher.forward(request, response);

            // TO-BE : HandlerAdapater와 ViewResolver 적용

            // HandlerAdapater
            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                    .filter(ha -> ha.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));

            ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

            // ViewResolver
            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), request, response);
            }

        } catch (Exception e) {
            log.error("exception occurred [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
