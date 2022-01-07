package com.employee.serviceImpl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repositary.EmployeeRepositary;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final List<Employee> empList = Arrays.asList(
			new Employee(1, "Gopal", "gopi@evoke.com", 123456L, "Dev", new Date(2022 - 01 - 03)),
			new Employee(2, "Gopal2", "gopi@evoke.com", 123445356L, "QA", new Date(2022 - 11 - 03)));

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
	public List<Integer> getAllEmployeesId() throws Exception {
		return employeeRepositary.getAllEmployeesId();
	}

	@Override
	public Page<Employee> getEmployeeUsingPagination(int offset, int pageSize) throws Exception {
		return employeeRepositary.findAll(PageRequest.of(offset, pageSize));
	}

	@Override
	public Page<Employee> getAllEMP(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return employeeRepositary.findAll(pageable);
	}

	@Override
	public List<Employee> getByDTO() {
		List<Employee> em=employeeRepositary.findAll();
		return empList;
	}

	@Override
	public Employee getByDTOEmp(Employee emp) {
		emp.setId(empList.size() + 1);
		emp.setCreatedOn(new Timestamp(Calendar.getInstance().getTime().getTime()));
		//empList.add(employeeRepositary.save(emp));
		return employeeRepositary.save(emp);
	}

}
