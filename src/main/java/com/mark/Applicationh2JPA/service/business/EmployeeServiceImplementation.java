package com.mark.Applicationh2JPA.service.business;

import java.util.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.mark.Applicationh2JPA.entity.Address;
import com.mark.Applicationh2JPA.entity.Asset;
import com.mark.Applicationh2JPA.service.repository.EmployeeRepository;
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
			employee.setAddress(repository.findById(employee.getEmployeeId()).orElse(null).getAddress());
			return repository.save(employee);
		}
		else {
			throw new EmployeeNotFoundException();
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


	public Employee findById(long id) {
		Optional<Employee> optionalEmployee = repository.findById(id);
		return optionalEmployee.orElseGet(() -> {throw new EmployeeNotFoundException();});
	}

	public Collection<Employee> findByName(String name){
		return repository.findByName(name);
	}


	public Employee addAddress(long employeeId, Address address) {
		Optional<Employee> optionalEmployee = repository.findById(employeeId);
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			address.setEmployee(employee);
			address.setId(employeeId);
			employee.setAddress(address);
			return repository.save(employee);

		}
		else {
			throw new EmployeeNotFoundException();
		}
	}

	public Employee addAsset(Long employeeId, Asset asset){
		Optional<Employee> optionalEmployee = repository.findById(employeeId);
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
//			for (Asset asset : assets) {
//				asset.setEmployee(employee);
//			}
			//address.setId(employeeId);
			employee.addAsset(asset);

			//employee.setAssets(new ArrayList<Asset>().add(asset));
//			if(repository.findById(employeeId).get().getAssets() != null){
//				employee.getAssets().addAll(assets);
//			}
//			else {
//				employee.setAssets(assets);
//			}
			return repository.save(employee);

		}
		else {
			throw new EmployeeNotFoundException();
		}
	}


}
