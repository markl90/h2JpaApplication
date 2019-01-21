package com.mark.Applicationh2JPA.entity;

import javax.persistence.*;

/**
 * Created by U.8902078 on 18/01/2019.
 */
@Entity
@Table(name = "Employee")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    private String name;

    public Employee(){}

    public Employee (long id, String name){
        this.employeeId = id;
        this.name = name;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                '}';
    }
}
