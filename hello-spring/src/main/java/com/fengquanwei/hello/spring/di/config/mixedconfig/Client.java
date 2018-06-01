package com.fengquanwei.hello.spring.di.config.mixedconfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 混合配置
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Config.class) // 【Option1:1】
@ContextConfiguration(locations = "classpath:spring-di-mixedconfig-root.xml") // 【Option1:2】
public class Client {
    @Autowired
    InterfaceB interfaceB;
    @Autowired
    InterfaceC interfaceC;

    /**
     * 使用【Option1:1】
     */
    @Test
    public void testJavaConfigImport() {
        // JavaConfig 导入 JavaConfig
        interfaceB.methodB();
        Assert.assertNotNull(interfaceB);

        // JavaConfig 导入 XML
        interfaceC.methodC();
        Assert.assertNotNull(interfaceC);
    }

    /**
     * 【Option1:2】
     */
    @Test
    public void testXmlConfigImport() {
        // XML 导入 JavaConfig
        interfaceB.methodB();
        Assert.assertNotNull(interfaceB);

        // XML 导入 XML
        interfaceC.methodC();
        Assert.assertNotNull(interfaceC);
    }
}
