package com.employee.service;

import java.util.List;

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

}
