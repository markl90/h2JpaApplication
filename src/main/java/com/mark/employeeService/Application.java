package com.mark.employeeService;

import com.mark.employeeService.service.EmployeeService;
import com.mark.employeeService.service.EmployeeServiceImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public EmployeeService getEmployeeService() {
		return new EmployeeServiceImplementation();
	}


}

