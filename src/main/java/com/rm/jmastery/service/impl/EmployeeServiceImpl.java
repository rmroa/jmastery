package com.rm.jmastery.service.impl;

import com.rm.jmastery.dto.EmployeeDto;
import com.rm.jmastery.repository.EmployeeRepository;
import com.rm.jmastery.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDto findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public EmployeeDto findByLastName(String lastname) {
        return employeeRepository.findByLastName(lastname);
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        return employeeRepository.save(employeeDto);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        return employeeRepository.update(employeeDto);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}

