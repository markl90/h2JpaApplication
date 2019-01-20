package com.mark.Applicationh2JPA.Service.Repository;

import com.mark.Applicationh2JPA.entity.Employee;

import java.util.List;

/**
 * Created by U.8902078 on 19/01/2019.
 */
public interface EmployeeRepository {

    List<Object[]> getAllEmployees();

    Object createEmployee(Employee employee);

    String updateEmployee();

    String deleteEmployee();
}
