package com.employee.repositary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.employee.model.Employee;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee, Integer> {

	List<Employee> findByName(String name);

	@Query("select e.id from Employee e")
	List<Integer> getAllEmployeesId();

	@Transactional
	@Modifying
	@Query("delete from Employee e where e.name=:name")
	void deleteByName(@Param("name") String name);

}
