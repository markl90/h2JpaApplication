package com.mark.Applicationh2JPA.Service.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.Applicationh2JPA.entity.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

/**
 * Created by U.8902078 on 19/01/2019.
 */
//@Repository
//@Primary
public class Employeeh2Repository {

//    @PersistenceContext
//    private EntityManager manager;
//
//
//    @Transactional
//    public List<Employee> getAllEmployees() {
//        String sql = "select e from Employee e";
//        TypedQuery<Employee> employeeQuery = manager.createQuery(sql, Employee.class);
//        return employeeQuery.getResultList();
//    }
//
//    @Transactional
//    public Employee createEmployee(Employee employee) {
//        manager.persist(employee);
////        String sql = "select MAX(employee_id) from Employee";
////        Query query = manager.createNativeQuery(sql);
//        return employee;
//    }
//
//
//    public String updateEmployee() {
//        return null;
//    }
//
//
//    public String deleteEmployee() {
//        return null;
//    }
}
