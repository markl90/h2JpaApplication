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
    private Long employeeId;

    private String name;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "addressId")
    private Address address;

    public Employee(){}

    public Employee (long id, String name){
        this.employeeId = id;
        this.name = name;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
