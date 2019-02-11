package com.mark.employeeService.repository;

import com.mark.employeeService.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	Collection<Employee> findByNameContaining(String name);

	Collection<Employee> findByNameContainingIgnoreCase(String name);

	//void removeAddress(Address address);
}
