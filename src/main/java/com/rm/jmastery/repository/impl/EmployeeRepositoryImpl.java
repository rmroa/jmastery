package com.rm.jmastery.repository.impl;

import com.rm.jmastery.dto.EmployeeDto;
import com.rm.jmastery.exception.EmployeeNotFoundException;
import com.rm.jmastery.exception.EmployeeRemovedException;
import com.rm.jmastery.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rm.jmastery.exception.ExceptionMessages.EMPLOYEE_CANNOT_BE_REMOVED;
import static com.rm.jmastery.exception.ExceptionMessages.EMPLOYEE_NOT_FOUND_BY_ID;
import static com.rm.jmastery.exception.ExceptionMessages.EMPLOYEE_NOT_FOUND_BY_LAST_NAME;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final String FIND_ALL_EMPLOYEE = "" +
            "SELECT " +
            "id, " +
            "firstname, " +
            "lastname," +
            "birthday," +
            "city," +
            "gender, " +
            "isDismissed " +
            "FROM employee";

    private static final String FIND_BY_ID = "" +
            "SELECT " +
            "id, " +
            "firstname, " +
            "lastname," +
            "birthday," +
            "city," +
            "gender," +
            "isDismissed " +
            "FROM employee " +
            "WHERE id = ?";

    private static final String FIND_BY_LAST_NAME = "" +
            "SELECT " +
            "id, " +
            "firstname, " +
            "lastname," +
            "birthday," +
            "city," +
            "gender," +
            "isDismissed " +
            "FROM employee " +
            "WHERE lastname = ?";

    private static final String SAVE_EMPLOYEE = "" +
            "INSERT INTO employee (firstname, lastname, birthday, city, gender) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_EMPLOYEE = "" +
            "UPDATE employee " +
            "SET firstname = ?, lastname = ?, birthday = ?, city = ?, gender = ?, isDismissed = ? " +
            "WHERE id = ?";

    private static final String DELETE_EMPLOYEE = "" +
            "DELETE from employee " +
            "WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeDto> findAll() {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEE, new BeanPropertyRowMapper<>(EmployeeDto.class));
    }

    @Override
    public EmployeeDto findById(Long id) {
        return jdbcTemplate.query(FIND_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(EmployeeDto.class))
                .stream().findAny().orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_BY_ID + id));
    }

    @Override
    public EmployeeDto findByLastName(String lastname) {
        return jdbcTemplate.query(FIND_BY_LAST_NAME, new Object[]{lastname}, new BeanPropertyRowMapper<>(EmployeeDto.class))
                .stream().findAny().orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_BY_LAST_NAME + lastname));
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        jdbcTemplate.update(SAVE_EMPLOYEE, employeeDto.getFirstname(), employeeDto.getLastname(), employeeDto.getBirthday(),
                employeeDto.getCity(), employeeDto.getGender().name());
        return employeeDto;
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        findById(employeeDto.getId());
        jdbcTemplate.update(UPDATE_EMPLOYEE, employeeDto.getFirstname(), employeeDto.getLastname(), employeeDto.getBirthday(),
                employeeDto.getCity(), employeeDto.getGender().name(), employeeDto.getIsDismissed(), employeeDto.getId());
        return employeeDto;
    }

    @Override
    public void deleteById(Long id) {
        EmployeeDto employeeDtoById = findById(id);
        if (employeeDtoById.getIsDismissed()) {
            jdbcTemplate.update(DELETE_EMPLOYEE, id);
        } else {
            throw new EmployeeRemovedException(EMPLOYEE_CANNOT_BE_REMOVED + employeeDtoById.getIsDismissed());
        }
    }
}

