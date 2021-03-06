package com.fengquanwei.hello.javase.jdk.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * 日志系统用法（java.util.logging）
 *
 * @author fengquanwei
 * @create 2017/4/6 23:24
 **/
public class LoggerUsage {
    /**
     * 测试自定义配置
     */
    public String testCustomLog(String param) throws IOException {
        // 重新读取配置文件
        InputStream configuration = ClassLoader.getSystemResourceAsStream("logging.properties");
        LogManager logManager = LogManager.getLogManager(); // 获取日志管理器
        logManager.readConfiguration(configuration);

        // 获取日志记录器
        Logger logger = Logger.getLogger(this.getClass().getName());

        // 添加日志处理器
        FileHandler fileHandler = new FileHandler("/opt/log/hello/tmp%g.log", true);
        fileHandler.setFormatter(new SimpleFormatter()); // 设置日志处理器的日志格式化器
        logger.addHandler(fileHandler);

        // 跟踪执行流
        logger.entering(this.getClass().getName(), "testCustomLog", new Object[]{param});

        // 测试打印
        logger.info("测试 logger.info");
        logger.config("测试 logger.config");

        // 测试指定级别的打印
        logger.log(Level.SEVERE, "测试指定 SEVERE 级别的日志");
        logger.log(Level.WARNING, "测试指定 WARNING 级别的日志");
        logger.log(Level.FINEST, "测试指定 FINEST 级别的日志"); // 为什么打不出来

        IOException ioException = new IOException("生成IO异常");
        logger.throwing(this.getClass().getName(), "testCustomLog", ioException);
        logger.log(Level.WARNING, "产生异常了", ioException);

        String result = "Hello, " + param;

        // 跟踪执行流
        logger.exiting(this.getClass().getName(), "testCustomLog", result);

        return result;
    }

    /**
     * 测试日志本地化
     */
    public void testLocalization() {
        Logger logger = Logger.getLogger("testLocalization", "logger_resource");
        logger.log(Level.INFO, "sayHi", "Lask");
    }

    /**
     * 测试文件处理器
     */
    public void testFileHandler() throws IOException {
        InputStream configuration = ClassLoader.getSystemResourceAsStream("logging.properties");
        LogManager logManager = LogManager.getLogManager(); // 获取日志管理器
        logManager.readConfiguration(configuration);

        Logger logger = Logger.getLogger("FileHandlerLogger");

        logger.setUseParentHandlers(false);

        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new FileHandler());

        for (int i = 0; i < 2000; i++) {
            logger.info(i + ": Test Limit.");
        }
    }

    /**
     * 尝试在程序启动时进行日志配置
     */
    public void testInitLogConfig() {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null) {
            try {
                // 设置根日志记录器的记录级别为 ALL
                Logger.getLogger("").setLevel(Level.ALL);

                // 构造日志文件处理器，FileHandler 默认日志处理级别为 ALL
                final int LOG_RATATION_COUNT = 30;
                final int LOG_LIMIT_COUNT = 50000;
                FileHandler fileHandler = new FileHandler("/opt/log/hello/hello.log", LOG_LIMIT_COUNT, LOG_RATATION_COUNT);
                fileHandler.setFormatter(new SimpleFormatter());

                Logger.getLogger("").addHandler(fileHandler);
            } catch (IOException e) {

            }
        }

        Logger logger = Logger.getLogger("testInitLogConfig");
        for (int i = 0; i < 2000; i++) {
            logger.info(i + ": Test Limit.");
            logger.fine(i + ": Test Limit.");
            logger.finest(i + ": Test Limit.");
        }
    }

    public static void main(String[] args) throws IOException {
//        Logger.getGlobal().info("测试默认日志记录器");

        // 测试默认日志配置
        Logger logger = Logger.getLogger(LoggerUsage.class.getName());
        logger.info("info test");
        logger.fine("fine test");
        logger.severe("severe test");

        LoggerUsage test = new LoggerUsage();
//        test.testCustomLog("Lask");
//        test.testLocalization();
//        test.testFileHandler();
//        test.testInitLogConfig();

    }
}
