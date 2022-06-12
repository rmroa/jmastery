package com.rm.jmastery.service;

import com.rm.jmastery.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> findAll();

    EmployeeDto findById(Long id);

    EmployeeDto findByLastName(String lastname);

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto update(EmployeeDto employeeDto);

    void deleteById(Long id);
}
