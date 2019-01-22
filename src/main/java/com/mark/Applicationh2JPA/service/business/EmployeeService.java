package com.mark.Applicationh2JPA.service.business;

import com.mark.Applicationh2JPA.entity.Address;
import com.mark.Applicationh2JPA.entity.Employee;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by U.8902078 on 19/01/2019.
 */
public interface EmployeeService {

    Collection<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Collection<Employee> deleteEmployee(Employee employee);

    Collection<Employee> deleteAllEmployees();

    Optional<Employee> findById(long id);

    Employee addAddress(long employeelId, Address address);
}
