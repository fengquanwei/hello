package com.fengquanwei.hello.spring.aop.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 切面
 *
 * @author fengquanwei
 * @create 2017/12/29 17:59
 **/
@Aspect
@Component
public class MyAspect {
    /**
     * 为 InterfaceA 的所有子类型引入 InterfaceB 接口
     * <p>
     * value：为哪个 bean 引入接口，+ 表示所有子类型而非接口本身
     * defaultImpl：引入接口的默认实现
     */
    @DeclareParents(value = "com.fengquanwei.hello.spring.aop.introduction.InterfaceA+", defaultImpl = ImplementB.class)
    public static InterfaceB interfaceB; // 标注的静态属性表示引入的接口类型
}
