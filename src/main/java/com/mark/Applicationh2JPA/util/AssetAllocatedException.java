package com.mark.Applicationh2JPA.util;

import com.mark.Applicationh2JPA.entity.Asset;
import com.mark.Applicationh2JPA.entity.Employee;

public class AssetAllocatedException extends RuntimeException {

    private Employee employee;
    private Asset asset;

    public AssetAllocatedException(Employee employee, Asset asset){
        super();
        this.employee = employee;
        this.asset = asset;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
