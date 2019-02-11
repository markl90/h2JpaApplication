package com.mark.employeeService.controller;

import com.mark.employeeService.entity.Address;
import com.mark.employeeService.entity.Asset;
import com.mark.employeeService.entity.Employee;
import com.mark.employeeService.service.EmployeeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

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

    @RequestMapping("/search")
    public Collection<Employee> findByName(@RequestParam("name") String name){
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
    public ResponseEntity deleteEmployee(@PathVariable("id") Long id){
        Employee employee = employeeService.deleteEmployee(id);
        String responseMessage = String.format("Employee: %s ID: %s has been deleted from records.", employee.getName(), employee.getEmployeeId());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public Collection<Employee> deleteAllEmployees(){
        return employeeService.deleteAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id){
        return employeeService.findById(id);
    }



    //Address
    @PutMapping("/{id}/address")
    public Employee addAddress(@PathVariable("id") Long employeelId, @RequestBody Address address){
        return employeeService.addAddress(employeelId, address);
    }

    @DeleteMapping("/{id}/address")
    public Employee deleteAddress(@PathVariable("id") Long employeelId){
        return employeeService.deleteAddress(employeelId);
    }

    @PutMapping("/{id}/assets")
    public Employee addAssets(@PathVariable("id") Long employeeId, @RequestBody Asset asset){
        return employeeService.addAsset(employeeId, asset);
    }



}
