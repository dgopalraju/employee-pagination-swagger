package com.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.customException.BusinessException;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	private static Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) throws Exception {
		log.info("Employee Details:::::{}", employee.toString());
		if (!employeeService.getAllEmployeesId().contains(employee.getId()))
			return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.CREATED);
		else
			return new ResponseEntity<BusinessException>(new BusinessException(409, "Employee already exists"),
					HttpStatus.CONFLICT);
	}

	@GetMapping("/employees")
	public ResponseEntity<?> getEmployees() throws Exception {
		if (!employeeService.getEmployees().isEmpty())
			return new ResponseEntity<List<Employee>>(employeeService.getEmployees(), HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(new BusinessException(404,"Employees not found"),HttpStatus.NOT_FOUND);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) throws Exception {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(new BusinessException(404,"Employee not found"),HttpStatus.NOT_FOUND);
	}

	@GetMapping("/employee/{name}")
	public ResponseEntity<?> getEmployeesByName(@PathVariable String name) throws Exception {
		List<Employee> employee = employeeService.getEmployeesByName(name);
		if (employee.size() > 0)
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(new BusinessException(404,"Employee not found"),HttpStatus.NOT_FOUND);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Integer id)
			throws Exception {
		if (employeeService.getEmployeeById(id) != null)
			return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(new BusinessException(404,"Employee not found"),HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) throws Exception {
		if (employeeService.getEmployeeById(id) != null) {
			employeeService.deleteEmployee(id);
			return new ResponseEntity<String>("Employee Deleted successfully", HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/employee/{name}")
	public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) throws Exception {
		if (!employeeService.getEmployeesByName(name).isEmpty()) {
			employeeService.deleteEmployeeByName(name);
			return new ResponseEntity<String>("Employee Deleted successfully", HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.NOT_FOUND);
	}
}
