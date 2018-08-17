package com.fengquanwei.hello.javase.concurrent.executor.invokeall;

/**
 * 结果
 *
 * @author fengquanwei
 * @create 2018/2/1 16:46
 **/
public class Result {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
