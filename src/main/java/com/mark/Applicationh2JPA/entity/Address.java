package com.mark.Applicationh2JPA.entity;

import javax.persistence.*;

/**
 * Created by U.8902078 on 22/01/2019.
 */
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

//    @OneToOne(cascade = {CascadeType.ALL})
//    private Employee employee;

    private String address;

    public Address(){}

    public Address(String address){
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }


//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
}
