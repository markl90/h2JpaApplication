package com.mark.Applicationh2JPA.service.repository;

import com.mark.Applicationh2JPA.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

/**
 * Created by U.8902078 on 19/01/2019.
 */
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	Collection<Employee> findByName(String name);
}
