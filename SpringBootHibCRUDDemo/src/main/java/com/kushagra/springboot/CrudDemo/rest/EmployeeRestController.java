package com.kushagra.springboot.CrudDemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kushagra.springboot.CrudDemo.entity.Employee;
import com.kushagra.springboot.CrudDemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	// inject employee dao
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;

	}

	// "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee if not found - " + employeeId);
		}

		return theEmployee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		// just in case someone pass an id.... so we set it to 0
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}

	@DeleteMapping("/employees/{employeeId}")
	public Employee deleteEmployee(@PathVariable int employeeId) {
		Employee theEmployee;
		theEmployee = employeeService.findById(employeeId);
		if (theEmployee == null) {
			throw new RuntimeException("No Such Employee exists - " + employeeId);
		}

		employeeService.deleteById(employeeId);

		return theEmployee;
	}
}
