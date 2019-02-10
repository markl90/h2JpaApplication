package com.mark.employeeService.service;

import java.util.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.mark.employeeService.entity.Address;
import com.mark.employeeService.entity.Asset;
import com.mark.employeeService.repository.EmployeeRepository;
import com.mark.employeeService.entity.Employee;
import com.mark.employeeService.util.exceptions.AssetAllocatedException;
import com.mark.employeeService.util.exceptions.EmployeeNotFoundException;
import com.mark.employeeService.util.exceptions.UndefinedSearchException;


public class EmployeeServiceImplementation implements EmployeeService {

	@Inject
	private EmployeeRepository repository;

	public EmployeeServiceImplementation(EmployeeRepository employeeRepository){
		this.repository = employeeRepository;

	}

	public EmployeeServiceImplementation(){}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new LinkedList<>();
		Iterable<Employee> employeeIterable = repository.findAll();
		employeeIterable.forEach(employees::add);

		return employees;
	}

	public Employee createEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Employee updateEmployee(Employee employee){
		if (repository.existsById(employee.getEmployeeId())) {
			// get existing address details and add to the updated employee object.
			employee.setAddress(repository.findById(employee.getEmployeeId()).orElse(null).getAddress());
			return repository.save(employee);
		}
		else {
			throw new EmployeeNotFoundException();
		}
	}

	public Employee deleteEmployee(Long id){
		Optional<Employee> employeeDeletion = repository.findById(id);
		if (employeeDeletion.isPresent()) {
			repository.delete(employeeDeletion.get());
		}
		return employeeDeletion.orElseThrow(() -> {throw new EmployeeNotFoundException();});
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

		if (name.length()>3) {
			String upperCase = name.replace(name.substring(0, 1), name.substring(0, 1).toUpperCase(Locale.ENGLISH));
			String lowerCase = name.replace(name.substring(0, 1), name.substring(0, 1).toLowerCase(Locale.ENGLISH));
			Collection<Employee> search = repository.findByNameContaining(upperCase);
			Collection<Employee> lowerCaseSearch = repository.findByNameContaining(lowerCase);
			search.addAll(lowerCaseSearch);
			return search;
		}
		else {
			throw new UndefinedSearchException();
		}



//		boolean isUpper = name.substring(0,1).toUpperCase();
//		Character.isUpperCase(name.charAt(0));
//
//		if(repository.findByNameContaining(name).isEmpty()){
//			String toUpperCase = name.substring(0,1).toUpperCase();
//			return repository.findByNameContaining(toUpperCase);
//		}
//		return repository.findByNameContaining(name);
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
