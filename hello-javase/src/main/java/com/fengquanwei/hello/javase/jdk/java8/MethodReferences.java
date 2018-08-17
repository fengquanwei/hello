package com.fengquanwei.hello.javase.jdk.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用 ::
 *
 * @author fengquanwei
 * @create 2018/2/28 20:18
 **/
public class MethodReferences {
    static class Car {
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("[Collide] Car: " + car);
        }

        public void follow(final Car car) {
            System.out.println("[Follow] This: " + this + ", Car: " + car);
        }

        public void repair() {
            System.out.println("[Repair] This: " + this);
        }
    }

    public static void main(String[] args) {
        // 构造器引用 Class< T >::new
        Car car = Car.create(Car::new);

        // 静态方法引用 Class::static_method
        List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);

        // 特定类的任意对象的方法引用 Class::method
        cars.forEach(Car::repair);

        // 特定对象的方法引用 instance::method
        cars.forEach(Car.create(Car::new)::follow);

        Arrays.asList(1, 2, 3).forEach(System.out::println);
    }
}
