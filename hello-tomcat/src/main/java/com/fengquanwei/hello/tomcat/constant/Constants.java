package com.fengquanwei.hello.tomcat.constant;

import java.io.File;

/**
 * 常量
 *
 * @author fengquanwei
 * @create 2017/8/28 14:15
 **/
public class Constants {
    /**
     * Working Directory 即 user.dir /Users/fengquanwei/IdeaProjects/tomcat-tutorial
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    public static final String PACKAGE = "com.fengquanwei.tomcat.tutorial";

    public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
}
