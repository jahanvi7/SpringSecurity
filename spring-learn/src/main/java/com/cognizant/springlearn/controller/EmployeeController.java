package com.cognizant.springlearn.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@RestController
public class EmployeeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		LOGGER.info("Employee Controller getAllEmployees START");
		LOGGER.debug("Employees : {}",employeeService.getAllEmployees());
		LOGGER.info("Employee Controller getAllEmployees END");
		return employeeService.getAllEmployees();
	}
	
	@PutMapping("/updateEmployee")
	public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
		employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
		employeeService.deleteEmployee(id);
	}
}
