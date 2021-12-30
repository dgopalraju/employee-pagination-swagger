package com.employee.serviceImpl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repositary.EmployeeRepositary;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepositary employeeRepositary;

	@Override
	public Employee addEmployee(Employee employee) throws Exception {
		employee.setCreatedOn(new Timestamp(Calendar.getInstance().getTime().getTime()));
		return employeeRepositary.save(employee);
	}

	@Override
	public List<Employee> getEmployees() throws Exception {
		return employeeRepositary.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeRepositary.findById(id).orElse(null);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) throws Exception {
		return employeeRepositary.findByName(name);
	}

	@Override
	public Employee updateEmployee(Employee employee, Integer id) throws Exception {
		employee.setCreatedOn(new Timestamp(Calendar.getInstance().getTime().getTime()));
		return employeeRepositary.save(employee);
	}

	@Override
	public void deleteEmployee(Integer id) throws Exception {
		employeeRepositary.deleteById(id);
	}

	@Override
	public void deleteEmployeeByName(String name) throws Exception {
		employeeRepositary.deleteByName(name);
	}

	@Override
	public List<Integer> getAllEmployeesId() throws Exception{
		return employeeRepositary.getAllEmployeesId();
	}

}
