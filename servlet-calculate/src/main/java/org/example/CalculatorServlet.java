package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class CalculatorServlet implements Servlet {

    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);

    private ServletConfig servletConfig;

    // Life Cycle과 관련된 메소드
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        log.info("init");

        this.servletConfig = servletConfig;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.info("service");

        // 클라이언트에서 서버로 operand1, operator, operand2 파라미터를 던져서
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        // 해당 도메인을 이용해서 계산을 시키고
        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        // 결과값을 클라이언트에게 전달
        PrintWriter writer = response.getWriter();
        writer.println(result);
    }

    @Override
    public void destroy() {
        // resource release
    }

    // Life Cycle과 관련 없는 메소드

    @Override
    public ServletConfig getServletConfig() {
        // 해당 인스턴스를 리턴할 때 사용
        return this.servletConfig;
    }


    @Override
    public String getServletInfo() {
        return null;
    }


}
