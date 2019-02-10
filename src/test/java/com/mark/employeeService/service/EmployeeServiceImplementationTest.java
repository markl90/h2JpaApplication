package com.mark.employeeService.service;

import com.mark.employeeService.entity.Address;
import com.mark.employeeService.entity.Asset;
import com.mark.employeeService.entity.Employee;
import com.mark.employeeService.repository.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplementationTest {

    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeServiceImplementation(employeeRepository);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(employeeRepository);
    }

    @Test
    public void getAllEmployees() {
        // Create test employees
        List<Employee> employeesList = new ArrayList<>();
        Employee testEmployee = new Employee(101, "Mark Ledwold");
        Employee secondTestEmployee = new Employee(102, "Harry Smith");
        Employee thirdTestEmployee = new Employee(103, "James Baker");
        Employee fourthTestEmployee = new Employee(103, "Ben Brown");
        // Add them to a list
        employeesList.add(testEmployee);
        employeesList.add(secondTestEmployee);
        employeesList.add(thirdTestEmployee);
        employeesList.add(fourthTestEmployee);
        // Set the response of the mock repository to return the list.
        when(employeeRepository.findAll()).thenReturn(employeesList);

        //Call the method and iterate over the response list to compare.
        List<Employee> response = employeeService.getAllEmployees();
        for (Employee employee: employeesList){
            assertEquals(employee.getEmployeeId(), response.get(employeesList.indexOf(employee)).getEmployeeId());
            assertEquals(employee.getName(), response.get(employeesList.indexOf(employee)).getName());
        }
    }

    @Test
    public void createEmployee() {
        //Create test employee and set the repository to return it.
        Employee TestEmployee = new Employee(101, "Mark Ledwold");
        when(employeeRepository.save(TestEmployee)).thenReturn(TestEmployee);
        // Call the service method.
        Employee createdEmployee =  employeeService.createEmployee(TestEmployee);
        //Check the name and ID in the response match expected.
        assertEquals(TestEmployee.getName(), createdEmployee.getName());
        assertEquals(TestEmployee.getEmployeeId(), createdEmployee.getEmployeeId());
    }

    @Test
    public void updateEmployee() {
        //Create test employee and set the response of the repository methods called.
        Employee testEmployee = new Employee(101, "Mark Ledwold");
        when(employeeRepository.existsById(testEmployee.getEmployeeId())).thenReturn(true);
        when(employeeRepository.findById(testEmployee.getEmployeeId())).thenReturn(java.util.Optional.of(testEmployee));
        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);
        // Call the service method.
        Employee createdEmployee =  employeeService.updateEmployee(testEmployee);
        //Check the name and ID in the response match expected.
        assertEquals(testEmployee.getName(), createdEmployee.getName());
        assertEquals(testEmployee.getEmployeeId(), createdEmployee.getEmployeeId());
    }

    @Test
    public void deleteEmployee() {
        //Create test employee for deletion & set the Optional of the repository method called.
        Employee testEmployee = new Employee(101, "Mark Ledwold");
        when(employeeRepository.findById(testEmployee.getEmployeeId())).thenReturn(java.util.Optional.of(testEmployee));
        // Call the service method.
        Employee deletedEmployee = employeeService.deleteEmployee(testEmployee.getEmployeeId());
        //Check the name and ID in the response match expected.
        assertEquals(testEmployee.getName(), deletedEmployee.getName());
        assertEquals(testEmployee.getEmployeeId(), deletedEmployee.getEmployeeId());
    }

    @Test
    public void deleteAllEmployees() {
        //Create and empty list and mock the return from the repository.
        List<Employee> employeesList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeesList);
        // Call the service method.
        Collection<Employee> responseList = employeeService.deleteAllEmployees();
        //Check and empty list has been returned.
        assertTrue(responseList.isEmpty());

    }

    @Test
    public void findById() {
        //Create test employee & set the Optional of the repository method called.
        Long testEmployeeId = Long.valueOf(989);
        Employee testEmployee = new Employee(testEmployeeId, "Mark Ledwold");
        when(employeeRepository.findById(testEmployeeId)).thenReturn(java.util.Optional.of(testEmployee));
        // Call the service method.
        Employee responseEmployee = employeeService.findById(testEmployeeId);
        //Check the name and ID in the response match expected.
        assertEquals(testEmployee.getName(), responseEmployee.getName());
        assertEquals(testEmployee.getEmployeeId(), responseEmployee.getEmployeeId());
    }

    @Test
    public void findByName() {
        //create test employees and add them to a collection.
        Employee testEmployee = new Employee(102,"Mark Ledwold");
        Employee secondTestEmployee = new Employee(102, "Harry Smith");
        Employee thirdTestEmployee = new Employee(103, "James Baker");
        List<Employee> employeeCollection = new ArrayList<Employee>();
        employeeCollection.add(testEmployee);
        employeeCollection.add(secondTestEmployee);
        employeeCollection.add(thirdTestEmployee);
        //Set the mock response for the repository to return the collection.
        when(employeeRepository.findByNameContaining("Mark")).thenReturn(employeeCollection);

        //Get the response from the service and convert to an array list.
        Collection<Employee> responseCollection = employeeService.findByName("Mark");
        ArrayList<Employee> responseList = new ArrayList<>();
        responseList.addAll(responseCollection);

        //Iterate through and check results
        for (Employee employee: employeeCollection) {
            assertEquals(employee.getEmployeeId(), responseList.get(employeeCollection.indexOf(employee)).getEmployeeId());
            assertEquals(employee.getName(), responseList.get(employeeCollection.indexOf(employee)).getName());
        }
    }

    @Test
    public void addAddress() {
        //Create a test employee and address.
        Employee testEmployee = new Employee(101, "Mark Ledwold");
        Address address = new Address("44 caclotte close");
        testEmployee.setAddress(address);

        //Set the mock response of repository methods called by the employee service.
        when(employeeRepository.findById(testEmployee.getEmployeeId())).thenReturn(java.util.Optional.of(testEmployee));
        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

        //Call addAddress and check that it is returned in the result.
        Employee employeeResult = employeeService.addAddress(testEmployee.getEmployeeId(), address);
        assertEquals(testEmployee.getAddress().getAddress(), employeeResult.getAddress().getAddress());
    }

    @Test
    public void addAsset() {
        //Create a test employee.
        Employee testEmployee = new Employee(101, "Mark Ledwold");
        //Create a an asset, add to a Set & add to test employee.
        Asset asset = new Asset("Laptop", "STRIDEX1192045687");
        Set<Asset> assets = new HashSet<>();
        testEmployee.setAssets(assets);

        //Set the mock response of repository methods called by the employee service.
        when(employeeRepository.findById(testEmployee.getEmployeeId())).thenReturn(java.util.Optional.of(testEmployee));
        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

        //Call addAsset and check that it is returned in the result.
        Employee employeeResult = employeeService.addAsset(testEmployee.getEmployeeId(), asset);
        assertTrue(employeeResult.getAssets().contains(asset));
    }
}