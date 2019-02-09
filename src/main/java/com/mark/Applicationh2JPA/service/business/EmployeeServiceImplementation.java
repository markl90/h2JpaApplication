package com.mark.Applicationh2JPA.service.business;

import java.util.*;

import javax.inject.Inject;

import com.mark.Applicationh2JPA.entity.Address;
import com.mark.Applicationh2JPA.entity.Asset;
import com.mark.Applicationh2JPA.service.repository.EmployeeRepository;
import com.mark.Applicationh2JPA.entity.Employee;
import com.mark.Applicationh2JPA.util.AssetAllocatedException;
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


	public Employee findById(Long id) {
		Optional<Employee> optionalEmployee = repository.findById(id);
		return optionalEmployee.orElseGet(() -> {throw new EmployeeNotFoundException();});
	}

	public Collection<Employee> findByName(String name){
		return repository.findByName(name);
	}


	public Employee addAddress(Long employeeId, Address address) {
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

			Employee currentOwner =  assetAlreadyAllocated(asset.getSerialCode());

			if (currentOwner != null){
				throw new AssetAllocatedException(currentOwner, asset);
			}
			asset.setEmployee(employee);
			employee.addAsset(asset);

			return repository.save(employee);

		}
		else {
			throw new EmployeeNotFoundException();
		}
	}


	public Employee assetAlreadyAllocated(String serialCode){
		List<Employee> employees = getAllEmployees();
		for (Employee employee : employees) {
			Set<Asset> assets = employee.getAssets();
			for (Asset asset: assets){
				if (asset.getSerialCode().equals(serialCode)){
					return employee;
				}
			}

		}
		return null;
	}


}
