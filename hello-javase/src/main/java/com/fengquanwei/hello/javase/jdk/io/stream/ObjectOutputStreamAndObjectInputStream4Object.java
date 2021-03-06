package com.fengquanwei.hello.javase.jdk.io.stream;


import com.fengquanwei.hello.javase.jdk.other.*;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

/**
 * 对象序列化
 * ObjectOutputStream and ObjectInputStream
 *
 * @author fengquanwei
 * @create 2017/6/24 23:10
 **/
public class ObjectOutputStreamAndObjectInputStream4Object {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 测试 Serializable 默认序列化
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15, harry);
        Manager tony = new Manager("Tony Tester", 400000, 1990, 3, 15, harry);

        Employee[] staffs = new Employee[3];
        staffs[0] = harry;
        staffs[1] = carl;
        staffs[2] = tony;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/tmp/staffs.txt"))) {
            out.writeObject(staffs);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("/tmp/staffs.txt"))) {
            Employee[] newStaffs = (Employee[]) in.readObject();
            System.out.println(Arrays.toString(newStaffs));
        }

        // 测试 Serializable 自定义序列化
        SerialEmployee serialEmployee = new SerialEmployee("Lynn", 20000, 2015, 7, 1);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/tmp/serialEmployee.txt"))) {
            out.writeObject(serialEmployee);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("/tmp/serialEmployee.txt"))) {
            SerialEmployee newSerialEmployee = (SerialEmployee) in.readObject();
            System.out.println(newSerialEmployee);
        }

        // 测试 Externalizable 自定义序列化
        ExternalEmployee externalEmployee = new ExternalEmployee("Lask", 20000, new Date());
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/tmp/externalEmployee.txt"))) {
            out.writeObject(externalEmployee);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("/tmp/externalEmployee.txt"))) {
            ExternalEmployee newExternalEmployee = (ExternalEmployee) in.readObject();
            System.out.println(newExternalEmployee);
        }

        // 测试唯一对象的序列化
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/tmp/HORIZONTAL.txt"))) {
            out.writeObject(Orientation.HORIZONTAL);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("/tmp/HORIZONTAL.txt"))) {
            Orientation horizontal = (Orientation) in.readObject();
            System.out.println(horizontal == Orientation.HORIZONTAL);
        }

        // 测试使用序列化克隆对象
        SerialCloneEmployee serialCloneEmployee = new SerialCloneEmployee("Lask", 40000, new Date());
        try {
            Object clone = serialCloneEmployee.clone();
            System.out.println(clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}