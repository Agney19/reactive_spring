package com.example.reactivespring.demo;

import com.example.reactivespring.demo.model.Employee;
import com.example.reactivespring.demo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository) {
		return args -> {
			employeeRepository
					.deleteAll()
					.subscribe(null, null, () -> {
						Stream.of(new Employee(UUID.randomUUID().toString(), "A", 100L),
								new Employee(UUID.randomUUID().toString(), "B", 102L),
								new Employee(UUID.randomUUID().toString(), "C", 103L),
								new Employee(UUID.randomUUID().toString(), "D", 104L))
								.forEach(employee -> {
									employeeRepository
											.save(employee)
											.subscribe(System.out::println);
								});
					});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
