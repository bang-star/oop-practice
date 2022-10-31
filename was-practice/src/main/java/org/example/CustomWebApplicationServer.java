package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {

    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {

        // 1. 해당 포트로 서버 소켓을 생성
        try(ServerSocket serverSocket = new ServerSocket(port)){

            // 2. 서버 소켓 실행 후 로그 기록
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            // 3. 클라이언트 소켓 대기
            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client");

            // 서버 소켓이 클라이언트를 기다리게 한다.
            while((clientSocket = serverSocket.accept()) != null){
                logger.info("[CustomWebApplicationServer] client connected!");

                /**
                 * STEP 2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
                 */

                new Thread(new ClientRequestHandler(clientSocket)).start();
            }
        }
    }
}
