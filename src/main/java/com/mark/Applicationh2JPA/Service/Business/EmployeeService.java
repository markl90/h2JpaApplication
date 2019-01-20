package com.mark.Applicationh2JPA.Service.Business;

import com.mark.Applicationh2JPA.entity.Employee;

import java.util.List;

/**
 * Created by U.8902078 on 19/01/2019.
 */
public interface EmployeeService {

    String getAllEmployees();

    String createEmployee(String employee);

    String updateEmployee(int id, String update);

    String deleteEmployee();
}
