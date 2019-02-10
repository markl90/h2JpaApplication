package com.mark.employeeService.service;

import com.mark.employeeService.entity.Employee;
import com.mark.employeeService.repository.EmployeeRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryTestImplementation implements EmployeeRepository {

    List<Employee> employeesList = new ArrayList<>();

    Employee testEmployee = new Employee(101, "Mark Ledwold");
    Employee secondTestEmployee = new Employee(102, "Harry Smith");
    Employee thirdTestEmployee = new Employee(103, "James Baker");
    Employee fourthTestEmployee = new Employee(103, "Ben Brown");



    public EmployeeRepositoryTestImplementation(){}

    public void addTestEmployees(){
        employeesList.add(testEmployee);
        employeesList.add(secondTestEmployee);
        employeesList.add(thirdTestEmployee);
        employeesList.add(fourthTestEmployee);
    }

    public void clearTestEmployees(){
        employeesList.clear();
    }

    public String hString(){
        return "hhhhhhhhhhhhhhh";
    }

    public Collection<Employee> findByNameContaining(String name) {
        return null;
    }


    public <S extends Employee> S save(S s) {
        return s;
    }

    public <S extends Employee> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    public Optional<Employee> findById(Long id) {
        Optional<Employee> optionalEmployee = null;
        for (Employee employee: employeesList){
            if(id == employee.getEmployeeId()){
               // optionalEmployee = Optional.of(employee);
            }
        }
        return optionalEmployee;
    }

    public boolean existsById(Long id) {
        for (Employee employee: employeesList){
            if(id == employee.getEmployeeId()){
                return true;
            }
        }
        return false;
    }

    public Iterable<Employee> findAll() {
        return employeesList;
    }

    public Iterable<Employee> findAllById(Iterable<Long> iterable) {
        return null;
    }

    public long count() {
        return employeesList.size();
    }

    public void deleteById(Long id) {
        for (Employee employee: employeesList){
            if(id == employee.getEmployeeId()){
                employeesList.remove(employee);
            }
        }

    }

    public void delete(Employee employee) {
        for (Employee employeeInList: employeesList){
            if(employeeInList == employee){
                employeesList.remove(employee);
            }
        }
    }

    public void deleteAll(Iterable<? extends Employee> iterable) {
        employeesList.clear();
    }

    public void deleteAll() {
        employeesList.clear();
    }
}
