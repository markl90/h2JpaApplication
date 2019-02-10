package com.mark.employeeService.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by U.8902078 on 18/01/2019.
 */
@Entity
@Table(name = "Employee")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String name;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "employee")
    private Address address;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "employee")
    private Set<Asset> assets;

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

    public boolean addressExists(){
        if (address != null){
            return true;
        }
        return false;
    }

    public Set<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    public void addAsset(Asset asset){
       assets.add(asset);
    }
}
