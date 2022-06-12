package com.rm.jmastery.repository;

import com.rm.jmastery.dto.EmployeeDto;

public interface EmployeeRepository extends CrudRepository<EmployeeDto, Long> {

    EmployeeDto findByLastName(String lastname);
}

