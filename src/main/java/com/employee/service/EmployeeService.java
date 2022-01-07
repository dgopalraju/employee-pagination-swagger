package com.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.employee.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee) throws Exception;

	List<Employee> getEmployees() throws Exception;

	Employee getEmployeeById(Integer id) throws Exception;

	List<Employee> getEmployeesByName(String name) throws Exception;

	Employee updateEmployee(Employee employee, Integer id) throws Exception;

	void deleteEmployee(Integer id) throws Exception;

	void deleteEmployeeByName(String name) throws Exception;

	List<Integer> getAllEmployeesId() throws Exception;

	Page<Employee> getEmployeeUsingPagination(int offset, int pageSize) throws Exception;

	Page<Employee> getAllEMP(Pageable pageable) throws Exception;

	List<Employee> getByDTO();
	
	Employee getByDTOEmp(Employee emp);

}
