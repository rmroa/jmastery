package com.rm.jmastery.controller;

import com.rm.jmastery.dto.EmployeeDto;
import com.rm.jmastery.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Get all employees.
     *
     * @return a list of objects EmployeeDto
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    /**
     * Get an employee by id.
     *
     * @param id the parameter by which we search for employee
     * @return an EmployeeDto object if any, otherwise a HttpStatus.NOT_FOUND
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    /**
     * Get an employee by lastname.
     *
     * @param lastname a parameter by which we search for employee
     * @return an EmployeeDto object if any, otherwise a HttpStatus.NOT_FOUND
     */
    @GetMapping("/filters")
    public ResponseEntity<EmployeeDto> findByLastName(@RequestParam(value = "lastname") String lastname) {
        return new ResponseEntity<>(employeeService.findByLastName(lastname), HttpStatus.OK);
    }

    /**
     * Saving an employee.
     *
     * @param employeeDto an EmployeeDto object to save
     * @return saved an EmployeeDto object
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.CREATED);
    }

    /**
     * Updating an employee.
     *
     * @param employeeDto an EmployeeDto object to update
     * @return updated an EmployeeDto object
     */
    @PutMapping
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.update(employeeDto), HttpStatus.OK);
    }

    /**
     * Deleting an employee by id
     *
     * @param id an EmployeeDto object identifier
     * @return HttpStatus.NO_CONTENT after successful removal
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

