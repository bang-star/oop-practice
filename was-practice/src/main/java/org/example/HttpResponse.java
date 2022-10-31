package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;

public class HttpResponse {
    // 로그 출력
    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private final DataOutputStream dos;

    public HttpResponse(DataOutputStream dos) {
        this.dos = dos;
    }


    // Header Setting(Header And Blank Line)
    public void response200Header(String contentType, int lengthOfBodyContent) {
        try {
            // Header Line
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: " + contentType + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            // Blank Line
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // Body Setting
    public void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
