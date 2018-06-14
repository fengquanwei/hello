package com.fengquanwei.hello.javaee.websocket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WebSocketServlet
 *
 * @author fengquanwei
 * @create 2018/2/24 10:50
 **/
@WebServlet("/websocket")
public class WebSocketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/websocket.jsp").forward(request, response);
    }
}
