package com.fengquanwei.hello.algorithm.roundrobin;

import java.util.LinkedList;
import java.util.List;

/**
 * 平滑的加权轮询调度器
 *
 * @author fengquanwei
 * @create 2018/3/1 19:06
 **/
public class SmoothWeightRoundRobinScheduler<T> {
    private ScheduleEntity<T>[] scheduleEntityArray;

    // 调度器只能通过调度器构造器构造
    private SmoothWeightRoundRobinScheduler() {
    }

    synchronized public T nextTarget() {
        int totalWeight = 0;
        int select = -1;

        for (int i = 0; i < scheduleEntityArray.length; i++) {
            ScheduleEntity<T> scheduleEntity = scheduleEntityArray[i];
            int currentWeight = scheduleEntity.getCurrentWeight();
            int weight = scheduleEntity.getWeight();

            scheduleEntity.setCurrentWeight(currentWeight + weight);
            totalWeight += weight;

            if (select == -1 || scheduleEntityArray[select].getCurrentWeight() < currentWeight) {
                select = i;
            }
        }

        scheduleEntityArray[select].setCurrentWeight(scheduleEntityArray[select].getCurrentWeight() - totalWeight);
        return scheduleEntityArray[select].getTarget();
    }

    /**
     * 调度器的构造器
     */
    public static class Builder<T> {
        private List<ScheduleEntity<T>> scheduleEntityList;

        public Builder<T> addEntity(T target, int weight) {
            if (null == scheduleEntityList) {
                scheduleEntityList = new LinkedList<>();
            }

            scheduleEntityList.add(new ScheduleEntity<>(target, weight));
            return this;
        }

        public SmoothWeightRoundRobinScheduler<T> build() {
            if (null == scheduleEntityList) {
                throw new RuntimeException("待调度实体不能为空");
            }

            SmoothWeightRoundRobinScheduler<T> scheduler = new SmoothWeightRoundRobinScheduler();
            scheduler.scheduleEntityArray = scheduleEntityList.toArray(new ScheduleEntity[scheduleEntityList.size()]);
            return scheduler;
        }
    }

    public static void main(String[] args) {
        Builder<String> builder = new Builder();
        SmoothWeightRoundRobinScheduler<String> scheduler = builder.addEntity("A", 4).addEntity("B", 2).addEntity("C", 1).build();

        for (int i = 0; i < 10; i++) {
            System.out.println(scheduler.nextTarget());
        }
    }

}
