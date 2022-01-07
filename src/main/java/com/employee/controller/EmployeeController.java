package com.employee.controller;

import java.util.List;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.customException.BusinessException;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
public class EmployeeController {
	private static Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	@ApiOperation(value = " Adding Employee" , notes = "New Employee Adding",tags = {"employee-controller"})
	@ApiResponses(value = {@ApiResponse(code = 505,message = "Employee contains duplicate")})
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee,
			@RequestHeader(value = "TraceID", defaultValue = "") String traceId) throws Exception {
		log.info("Employee Details:::::{}", employee.toString());
		log.info("traceId value=====>>>" + traceId);
		if (traceId.isEmpty()) {
			traceId = UUID.randomUUID().toString();
			log.info("traceId value=====>>>" + UUID.randomUUID().toString());
			log.info("traceId value=====>>>" + traceId);
		}
		if (!employeeService.getAllEmployeesId().contains(employee.getId()))
			return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.CREATED);
		else
			return new ResponseEntity<BusinessException>(new BusinessException(409, "Employee already exists", traceId),
					HttpStatus.CONFLICT);
	}

	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployees() throws Exception {
		if (!employeeService.getEmployees().isEmpty())
			return new ResponseEntity<List<Employee>>(employeeService.getEmployees(), HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(
					new BusinessException(404, "Employees not found", UUID.randomUUID().toString()),
					HttpStatus.NOT_FOUND);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) throws Exception {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(
					new BusinessException(404, "Employee not found", UUID.randomUUID().toString()),
					HttpStatus.NOT_FOUND);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) throws Exception {
		if (employeeService.getEmployeeById(id) != null)
			return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(
					new BusinessException(404, "Employee not found", UUID.randomUUID().toString()),
					HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) throws Exception {
		if (employeeService.getEmployeeById(id) != null) {
			employeeService.deleteEmployee(id);
			return new ResponseEntity<String>("Employee Deleted successfully", HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.NOT_FOUND);
	}
	
	
/*	@GetMapping("/employee/{name}")
	public ResponseEntity<?> getEmployeesByName(@PathVariable String name) throws Exception {
		List<Employee> employee = employeeService.getEmployeesByName(name);
		if (employee.size() > 0)
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(
					new BusinessException(404, "Employee not found", UUID.randomUUID().toString()),
					HttpStatus.NOT_FOUND);
	} */

	

/*	@DeleteMapping("/employee/{name}")
	public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) throws Exception {
		if (!employeeService.getEmployeesByName(name).isEmpty()) {
			employeeService.deleteEmployeeByName(name);
			return new ResponseEntity<String>("Employee Deleted successfully", HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/employees/{offset}/{pageSize}")
	public ResponseEntity<?> withPagination(@PathVariable int offset, @PathVariable int pageSize) throws Exception {
		Page<Employee> empy = employeeService.getEmployeeUsingPagination(offset, pageSize);
		if (!empy.isEmpty())
			return new ResponseEntity<Page<Employee>>(empy, HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(new BusinessException(505,
					"No Records available to display in this page", UUID.randomUUID().toString()),
					HttpStatus.NOT_FOUND);
	}

	@GetMapping("/employeePage")
	public ResponseEntity<?> getTestingDetails(@PageableDefault(page = 0, size = 5) Pageable pageable)
			throws Exception {
		return new ResponseEntity<Page<Employee>>(employeeService.getAllEMP(pageable), HttpStatus.OK);
	}

	@GetMapping("/emp")
	public ResponseEntity<List<Employee>> getByDTO() {
		return new ResponseEntity<List<Employee>>(employeeService.getByDTO(), HttpStatus.OK);
	}

	@PostMapping("/empl")
	public ResponseEntity<Employee> getByDTOEmp(@RequestBody Employee emp) {
		return new ResponseEntity<Employee>(employeeService.getByDTOEmp(emp), HttpStatus.OK);
	} */
}
