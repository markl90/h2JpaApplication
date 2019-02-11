package com.mark.employeeService.service;

import com.mark.employeeService.entity.Address;
import com.mark.employeeService.entity.Asset;
import com.mark.employeeService.entity.Employee;
import com.mark.employeeService.repository.AddressRepository;
import com.mark.employeeService.repository.EmployeeRepository;
import com.mark.employeeService.util.exceptions.AssetAllocatedException;
import com.mark.employeeService.util.exceptions.EmployeeNotFoundException;
import com.mark.employeeService.util.exceptions.UndefinedSearchException;

import javax.inject.Inject;
import java.util.*;


public class EmployeeServiceImplementation implements EmployeeService {

	@Inject
	private EmployeeRepository repository;

	@Inject
	private AddressRepository addressRepository;

	public EmployeeServiceImplementation(EmployeeRepository employeeRepository){
		this.repository = employeeRepository;
	}

	public EmployeeServiceImplementation(){}

	public List<Employee> getAllEmployees() {
		Iterable<Employee> employeeIterable = repository.findAll();
		List<Employee> employees = new LinkedList<>();
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
		return employeeDeletion.orElseGet(() -> {throw new EmployeeNotFoundException();});
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
			return repository.findByNameContainingIgnoreCase(name);
		}
		else {
			throw new UndefinedSearchException();
		}
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

	public Employee deleteAddress(Long employeeId){
		Optional<Employee> employeeDeletion = repository.findById(employeeId);
		if (employeeDeletion.isPresent()) {
			Employee employee = employeeDeletion.get();
			Address addressToDelete = employee.getAddress();
			employee.setAddress(null);
			addressRepository.delete(addressToDelete);
			return repository.findById(employeeId+1).get();
		}
		throw new EmployeeNotFoundException();
	}

	public Employee addAsset(Long employeeId, Asset asset){

		Optional<Employee> optionalEmployee = repository.findById(employeeId);
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();

			// Check asset serial code not already allocated.
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
