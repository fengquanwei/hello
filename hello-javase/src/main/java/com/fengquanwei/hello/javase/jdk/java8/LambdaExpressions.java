package com.fengquanwei.hello.javase.jdk.java8;

import java.util.Arrays;

/**
 * Lambda 表达式 (parameters) ->{ statements; }
 *
 * @author fengquanwei
 * @create 2018/2/28 20:05
 **/
public class LambdaExpressions {
    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }

    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        MathOperation mathOperation1 = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };

        MathOperation mathOperation2 = (int a, int b) -> {
            return a + b;
        };

        // 类型声明可选
        MathOperation mathOperation3 = (a, b) -> {
            return a + b;
        };

        // 方法体大括号可选
        MathOperation mathOperation4 = (a, b) -> a + b;

        System.out.println(operate(1, 2, mathOperation1));
        System.out.println(operate(1, 2, mathOperation2));
        System.out.println(operate(1, 2, mathOperation3));
        System.out.println(operate(1, 2, mathOperation4));

        // 参数圆括号可选
        GreetingService greetingService = message -> System.out.println("Hello " + message);
        greetingService.sayMessage("Lask");

        Arrays.asList(1, 2, 3).forEach(i -> System.out.println(i));
    }
}
