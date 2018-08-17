package com.fengquanwei.hello.algorithm.hash;

/**
 * Employee
 *
 * @author fengquanwei
 * @create 2018/2/25 14:32
 **/
public class Employee {
    private String name;
    private double salary;
    private int seniority;

    public Employee(String name, double salary, int seniority) {
        this.name = name;
        this.salary = salary;
        this.seniority = seniority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (Double.compare(employee.salary, salary) != 0) return false;
        if (seniority != employee.seniority) return false;
        return name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + seniority;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", seniority=" + seniority +
                '}';
    }
}
