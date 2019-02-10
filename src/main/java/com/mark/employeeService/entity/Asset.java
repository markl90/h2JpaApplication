package com.mark.employeeService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by U.8902078 on 24/01/2019.
 */
@Entity
@Table(name = "Assets")
public class Asset {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;

    private String assetType;

    private String serialCode;

    public Asset(){}

    public Asset(String assetType, String serialCode){
        this.assetType = assetType;
        this.serialCode =serialCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }
}
