package com.mark.Applicationh2JPA.service.repository;

import com.mark.Applicationh2JPA.entity.Employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by U.8902078 on 19/01/2019.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public Employee findByName(String name);
}
