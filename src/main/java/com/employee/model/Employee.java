package com.employee.model;

import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
	private int id;
	private String name;
	private String email;
	private Long phoneNumber;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdOn;
}
