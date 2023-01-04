package com.example.demo.emp.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class EmpVO { 
	String employeeId;
	String firstName;
	String lastName;
	String email;
	Date hireDate;
	int salary;
	String jobId;
	String departmentId;
	String managerId;
	String csh;
}
