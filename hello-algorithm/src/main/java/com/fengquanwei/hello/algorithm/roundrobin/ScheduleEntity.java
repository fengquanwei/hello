package com.fengquanwei.hello.algorithm.roundrobin;

/**
 * 调度实体
 *
 * @author fengquanwei
 * @create 2018/3/1 19:07
 **/
public class ScheduleEntity<T> {
    private T target;
    private int weight;
    private int currentWeight;

    public ScheduleEntity(T target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }
}
