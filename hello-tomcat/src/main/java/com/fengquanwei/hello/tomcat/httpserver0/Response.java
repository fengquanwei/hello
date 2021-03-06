package com.fengquanwei.hello.tomcat.httpserver0;

import com.fengquanwei.hello.tomcat.constant.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 响应
 *
 * @author fengquanwei
 * @create 2017/8/24 14:38
 **/
public class Response {
    private static final int BUFFER_SIZE = 1024;

    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            File file = new File(Constants.WEB_ROOT, request.getUri());
            if (file.exists()) {
                try (FileInputStream inputStream = new FileInputStream(file)) {
                    int read = inputStream.read(buffer, 0, BUFFER_SIZE);
                    while (read != -1) {
                        output.write(buffer, 0, read);
                        read = inputStream.read(buffer, 0, BUFFER_SIZE);
                    }
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Conent-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
