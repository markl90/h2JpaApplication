package com.mark.Applicationh2JPA.entity;

import javax.persistence.*;

/**
 * Created by U.8902078 on 24/01/2019.
 */
@Entity
@Table(name = "Assets")
public class Asset {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;

    private String assetType;

    public Asset(){}

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
}
