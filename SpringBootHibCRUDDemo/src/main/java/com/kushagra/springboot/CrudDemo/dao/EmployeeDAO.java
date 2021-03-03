package com.kushagra.springboot.CrudDemo.dao;

import java.util.List;

import com.kushagra.springboot.CrudDemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();

	public Employee findById(int theId);

	public void save(Employee theEmployee);

	public void deleteByID(int theId);
}
