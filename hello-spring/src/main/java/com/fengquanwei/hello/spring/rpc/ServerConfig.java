package com.fengquanwei.hello.spring.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Config
 *
 * @author fengquanwei
 * @create 2018/7/24 11:06
 **/
@Configuration
public class ServerConfig {
    @Bean
    public InterfaceA interfaceA() {
        return new ImplementA();
    }

    /**
     * 导出 RMI 服务
     */
    @Bean
    public RmiServiceExporter rmiServiceExporter(InterfaceA interfaceA) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(interfaceA);
        rmiServiceExporter.setServiceName("interfaceA");
        rmiServiceExporter.setServiceInterface(InterfaceA.class);
//        rmiServiceExporter.setRegistryHost("localhost"); // 不设置时会新建，设置时会去连接（由于未启动定会连接异常）
        rmiServiceExporter.setRegistryPort(8888);

        return rmiServiceExporter;
    }
}
