package com.eventi.left.emp.service;

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
	Integer salary;
	String jobId;
	String departmentId;
	String managerId;
}