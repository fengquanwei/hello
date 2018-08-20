package com.fengquanwei.hello.maven;

import org.junit.Assert;
import org.junit.Test;

/**
 * HelloWorldTest
 *
 * @author fengquanwei
 * @create 2018/8/20 16:57
 **/
public class HelloWorldTest {

    @Test
    public void testSayHello() {
        HelloWorld helloWorld = new HelloWorld();
        String result = helloWorld.sayHello();
        Assert.assertEquals("Hello Maven", result);
    }
}
