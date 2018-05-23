package com.fengquanwei.hello.spring.di.advanced.conditional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 条件化的 bean
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    InterfaceA anInterfaceA;

    @Test
    public void test() {
        anInterfaceA.methodA();
        Assert.assertNotNull(anInterfaceA);
    }
}
