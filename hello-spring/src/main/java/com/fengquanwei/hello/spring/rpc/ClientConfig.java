package com.fengquanwei.hello.spring.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * ClientConfig
 *
 * @author fengquanwei
 * @create 2018/7/24 11:12
 **/
@Configuration
public class ClientConfig {
    /**
     * 装配 RMI 服务
     */
    @Bean
    public RmiProxyFactoryBean proxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:8888/interfaceA");
        rmiProxyFactoryBean.setServiceInterface(InterfaceA.class);

        return rmiProxyFactoryBean;
    }
}
