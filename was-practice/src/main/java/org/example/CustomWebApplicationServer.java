package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {

    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

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
                 * STEP 3 - Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
                 */

                // As-is
                // new Thread(new ClientRequestHandler(clientSocket)).start();

                // To-be
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}
