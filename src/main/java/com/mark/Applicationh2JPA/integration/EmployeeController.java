package com.mark.Applicationh2JPA.integration;

import com.mark.Applicationh2JPA.entity.Address;
import com.mark.Applicationh2JPA.service.business.EmployeeService;
import com.mark.Applicationh2JPA.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
    public ResponseEntity updateEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employee){
        employee.setEmployeeId(employeeId);
        return ResponseEntity.ok().body(employeeService.updateEmployee(employee));
        //return employeeService.updateEmployee(employee);
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

    @PutMapping("/{id}/address")
    public Employee addAddress(@PathVariable("id") long employeelId, @RequestBody Address address){
        return employeeService.addAddress(employeelId, address);
    }

}
