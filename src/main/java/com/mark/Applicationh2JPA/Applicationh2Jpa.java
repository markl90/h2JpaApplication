package com.mark.Applicationh2JPA;

import com.mark.Applicationh2JPA.Service.Business.EmployeeService;
import com.mark.Applicationh2JPA.Service.Business.EmployeeServiceImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@SpringBootApplication
public class Applicationh2Jpa {

	public static void main(String[] args) {
		SpringApplication.run(Applicationh2Jpa.class, args);
	}

	@Bean
	public EmployeeService getEmployeeService() {
		return new EmployeeServiceImplementation();
	}

//	@Bean
//	public EmployeeRepository getEmployeeRepository() {
//		return new Employeeh2Repository();
//	}

}

