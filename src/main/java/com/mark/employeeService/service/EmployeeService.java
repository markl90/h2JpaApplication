package com.mark.employeeService.service;

import com.mark.employeeService.entity.Address;
import com.mark.employeeService.entity.Asset;
import com.mark.employeeService.entity.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee deleteEmployee(Long id);

    Collection<Employee> deleteAllEmployees();

    Employee findById(Long id);

    Employee addAddress(Long employeelId, Address address);

    Collection<Employee> findByName(String name);

    Employee addAsset(Long employeeId, Asset assets);
}
