package org.example;


import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable {  // 쓰레드를 위해 Runnable 상속

    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);

    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        /**
         * STEP 2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
         */

        // 클라이언트 Thread 를 구분하기 위한 용도
        logger.info("[ClientRequestHandler] new client {} started.", Thread.currentThread().getName());

        try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
            // Line by Line로 읽기 위해 InputStream -> Reader로 변환
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            // OutStream도 DataOutputStream으로 감싸준다.
            DataOutputStream dos = new DataOutputStream(out);

            HttpRequest httpRequest = new HttpRequest(br);

            // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
            if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                QueryStrings queryStrings = httpRequest.getQueryStrings();

                int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                String operator = queryStrings.getValue("operator");
                int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
                byte[] body = String.valueOf(result).getBytes();        // 연산된 결과 값을 byte 배열로 변환

                // Response Setting
                HttpResponse response = new HttpResponse(dos);
                response.response200Header("application/json", body.length);
                response.responseBody(body);
            }

        } catch (IOException e) {
            // 에러 핸들링을 위한 것이 아니므로 로그만 남긴다.
            logger.error(e.getMessage());
        }
    }
}
