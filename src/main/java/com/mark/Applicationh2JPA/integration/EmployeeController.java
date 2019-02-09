package com.mark.Applicationh2JPA.integration;

import com.mark.Applicationh2JPA.entity.Address;
import com.mark.Applicationh2JPA.entity.Asset;
import com.mark.Applicationh2JPA.entity.Employee;
import com.mark.Applicationh2JPA.service.business.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Set;

/**
 * Created by U.8902078 on 19/01/2019.
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {


    @Inject
    private EmployeeService employeeService;

    
    @GetMapping
    public Collection<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @RequestMapping("/byName/{name}")
    public Collection<Employee> findByName(@PathVariable("name") String name){
        return employeeService.findByName(name);
    }

    @PostMapping(consumes = "application/json")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employee){
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
    public Employee findById(@PathVariable("id") Long id){
        return employeeService.findById(id);
    }

    @PutMapping("/{id}/address")
    public Employee addAddress(@PathVariable("id") Long employeelId, @RequestBody Address address){
        return employeeService.addAddress(employeelId, address);
    }

    @PutMapping("/{id}/assets")
    public Employee addAssets(@PathVariable("id") Long employeeId, @RequestBody Asset asset){
        return employeeService.addAsset(employeeId, asset);
    }

}
