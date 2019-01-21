package com.mark.Applicationh2JPA.Integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BaseJsonNode;
import com.mark.Applicationh2JPA.Service.Business.EmployeeService;
import com.mark.Applicationh2JPA.Service.Repository.EmployeeRepository;
import com.mark.Applicationh2JPA.entity.Employee;
import org.hibernate.service.spi.InjectService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by U.8902078 on 19/01/2019.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Inject
    private EmployeeService employeeService;

    
    @GetMapping
    public Collection<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

//    @RequestMapping("/byName/{name}")
//    public Employee findByName(@PathParam("name") String name){
//        return employeeRepo.findByName(name);
//    }


    @PostMapping(consumes = "application/json")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }


    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") int employeeId, @RequestBody Employee employee){
        employee.setEmployeeId(employeeId);
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public Collection<Employee> deleteEmployee(@PathVariable("id") long id){
        return employeeService.deleteEmployee(new Employee(id, null));
    }

    @DeleteMapping("/deleteAll")
    public Collection<Employee> deleteAllEmployees(){
        return employeeService.deleteAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employee> findById(@PathVariable("id") long id){
        return employeeService.findById(id);
    }


}
