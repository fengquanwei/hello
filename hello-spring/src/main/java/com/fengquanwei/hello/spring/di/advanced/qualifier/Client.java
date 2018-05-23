package com.fengquanwei.hello.spring.di.advanced.qualifier;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 限定符
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    @Qualifier("A1")
    InterfaceA interfaceA;

    @Autowired
    @MyQualifier1
    @MyQualifier2
    InterfaceB interfaceB;

    @Test
    public void test() {
        interfaceA.methodA();
        interfaceB.methodB();

        Assert.assertNotNull(interfaceA);
        Assert.assertNotNull(interfaceB);
    }
}
