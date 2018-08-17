package com.fengquanwei.hello.tomcat.connector2.startup;

import com.fengquanwei.hello.tomcat.connector2.connector.HttpConnector;

/**
 * 启动类
 *
 * @author fengquanwei
 * @create 2017/8/30 14:38
 **/
public class Bootstrap {
    /**
     * Debug Configuration
     * VM options: -classpath /Users/fengquanwei/IdeaProjects/hello.tomcat/target/classes:/Users/fengquanwei/.m2/repository/javax/servlet/javax.servlet-api/3.0.1/javax.servlet-api-3.0.1.jar:./
     * <p>
     * Test URL
     * http://localhost:8081/hello.html
     * http://localhost:8081/servlet/com.fengquanwei.hello.tomcat.servlet.PrimitiveServlet
     * http://localhost:8081/servlet/com.fengquanwei.hello.tomcat.servlet.ModernServlet
     */
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
