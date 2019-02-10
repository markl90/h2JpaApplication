package com.mark.employeeService.controller;

import com.mark.employeeService.Applicationh2Jpa;
import com.mark.employeeService.entity.Employee;
import com.mark.employeeService.repository.EmployeeRepository;
import com.mark.employeeService.service.EmployeeRepositoryTestImplementation;
import com.mark.employeeService.service.EmployeeServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;
import java.util.Collection;

public class EmployeeControllerTest {


    @Configuration
    @Import(Applicationh2Jpa.class) // the actual configuration
    public static class TestConfig
    {
        @Bean
        public EmployeeRepository employeeRepository()
        {
            return new EmployeeRepositoryTestImplementation();
        }
    }

    @Inject
    private  EmployeeServiceImplementation employeeService;
    @Autowired
    private  EmployeeController employeeController;
    @Inject
    private  EmployeeRepository employeeRepository;


//    public EmployeeControllerTest(){
//        employeeRepository = new EmployeeRepositoryTestImplementation();
//        employeeService = new EmployeeServiceImplementation(employeeRepository);
//        employeeController = new EmployeeController();
//    }




    @Before
    public void setUp() throws Exception {
//        employeeRepository = new EmployeeRepositoryTestImplementation();
//        employeeService = new EmployeeServiceImplementation(employeeRepository);
//        employeeController = new EmployeeController();
    }

    @Test
    public void getAllEmployees() {
        employeeController = new EmployeeController();
        employeeRepository = new EmployeeRepositoryTestImplementation();
        employeeService = new EmployeeServiceImplementation();
       Collection<Employee> employees = employeeController.getAllEmployees();

     //  employeeRepository



    }

    @Test
    public void findByName() {
    }

    @Test
    public void createEmployee() {
    }

    @Test
    public void updateEmployee() {
    }

    @Test
    public void deleteEmployee() {
    }

    @Test
    public void deleteAllEmployees() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void addAddress() {
    }

    @Test
    public void addAssets() {
    }
}