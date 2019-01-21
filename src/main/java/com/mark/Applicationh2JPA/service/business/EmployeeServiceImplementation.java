package com.mark.Applicationh2JPA.service.business;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.mark.Applicationh2JPA.service.Repository.EmployeeRepository;
import com.mark.Applicationh2JPA.entity.Employee;
import com.mark.Applicationh2JPA.util.EmployeeNotFoundException;

/**
 * Created by U.8902078 on 19/01/2019.
 */
public class EmployeeServiceImplementation implements EmployeeService {

	@Inject
	private EmployeeRepository repository;

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new LinkedList<>();
		Iterable<Employee> employeeIt = repository.findAll();
		employeeIt.forEach(employees::add);

		return employees;
	}


	public Employee createEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Employee updateEmployee(Employee employee){
		if (repository.existsById(employee.getEmployeeId())) {
			return repository.save(employee);
		}
		else {
			//return new ResponseEntity(HttpStatus.NOT_FOUND);
			throw new EmployeeNotFoundException();
			//String.valueOf(HttpStatus.NOT_FOUND), new RuntimeException()
		}
	}

	public List<Employee> deleteEmployee(Employee employee){
		repository.delete(employee);
		return getAllEmployees();
	}

	public List<Employee> deleteAllEmployees(){
		repository.deleteAll();
		return getAllEmployees();
	}


	public Optional<Employee> findById(long id) {
		return repository.findById(id);
	}


}
