package com.mark.Applicationh2JPA.Service.Business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.Applicationh2JPA.Service.Repository.EmployeeRepository;
import com.mark.Applicationh2JPA.entity.Employee;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by U.8902078 on 19/01/2019.
 */
public class EmployeeServiceImplementation implements EmployeeService{

    @Inject
    private EmployeeRepository repository;

    @Inject
    ObjectMapper objectMapper;

    public String getAllEmployees() {
        List<Object[]> rows = repository.getAllEmployees();
        List<Employee> results = new ArrayList<>(rows.size());
        for (Object[] row : rows ){
            results.add(new Employee((int)row[0],(String)row[1]));
        }
        String jsonString = "";
        for (Object employee : results) {
            try {
                jsonString += objectMapper.writeValueAsString(employee);
            } catch ( JsonProcessingException e ) {
                e.printStackTrace();
            }
        }
        return jsonString;
    }

    public String createEmployee(String employee) {
        Employee newEmployee = null;
        String newEmployeeJson = "";
        try {
            newEmployee = objectMapper.readValue(employee, Employee.class);
            newEmployeeJson = objectMapper.writeValueAsString(repository.createEmployee(newEmployee));
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return newEmployeeJson;
    }

    public String updateEmployee(int id, String update) {

        return null;
    }



    public String deleteEmployee() {
        return null;
    }
}
