package com.rm.jmastery.service;

import com.rm.jmastery.dto.EmployeeDto;
import com.rm.jmastery.repository.EmployeeRepository;
import com.rm.jmastery.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private static final Long FIRST_EMPLOYEE_ID = 1L;
    private static final Long SECOND_EMPLOYEE_ID = 2L;
    private static final Long THIRD_EMPLOYEE_ID = 3L;
    private static final Long DISMISSED_EMPLOYEE_ID = 4L;
    private static final String FIRST_EMPLOYEE_NAME = "First name";
    private static final String SECOND_EMPLOYEE_NAME = "Second name";
    private static final String THIRD_EMPLOYEE_NAME = "Third name";
    private static final String DISMISSED_EMPLOYEE_NAME = "Dismissed employee name";
    private static final String UPDATED_EMPLOYEE_NAME = "Updated name";
    private static final String FIRST_EMPLOYEE_LAST_NAME = "First last name";
    private static final String SECOND_EMPLOYEE_LAST_NAME = "Second last Name";
    private static final String THIRD_EMPLOYEE_LAST_NAME = "Third last name";
    private static final String DISMISSED_EMPLOYEE_LAST_NAME = "Dismissed employee last name";
    private static final String UPDATED_EMPLOYEE_LAST_NAME = "Updated last name";
    private static final Boolean FIRST_EMPLOYEE_IS_DISMISSED = Boolean.FALSE;
    private static final Boolean SECOND_EMPLOYEE_IS_DISMISSED = Boolean.FALSE;
    private static final Boolean THIRD_EMPLOYEE_IS_DISMISSED = Boolean.FALSE;
    private static final Boolean DISMISSED_EMPLOYEE = Boolean.TRUE;
    private static final Boolean UPDATED_IS_DISMISSED = Boolean.FALSE;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeDto employeeDtoFirst;
    private EmployeeDto employeeDtoSecond;
    private EmployeeDto employeeDtoThird;
    private EmployeeDto employeeDtoUpdated;
    private EmployeeDto employeeDtoDismissed;

    private List<EmployeeDto> employeeDtoList;

    @BeforeEach
    void setup() {

        employeeDtoFirst = new EmployeeDto(FIRST_EMPLOYEE_NAME, FIRST_EMPLOYEE_LAST_NAME, FIRST_EMPLOYEE_IS_DISMISSED);
        employeeDtoSecond = new EmployeeDto(SECOND_EMPLOYEE_NAME, SECOND_EMPLOYEE_LAST_NAME, SECOND_EMPLOYEE_IS_DISMISSED);
        employeeDtoThird = new EmployeeDto(THIRD_EMPLOYEE_NAME, THIRD_EMPLOYEE_LAST_NAME, THIRD_EMPLOYEE_IS_DISMISSED);
        employeeDtoUpdated = new EmployeeDto(UPDATED_EMPLOYEE_NAME, UPDATED_EMPLOYEE_LAST_NAME, UPDATED_IS_DISMISSED);
        employeeDtoDismissed = new EmployeeDto(DISMISSED_EMPLOYEE_NAME, DISMISSED_EMPLOYEE_LAST_NAME, DISMISSED_EMPLOYEE);

        employeeDtoList = Arrays.asList(employeeDtoFirst, employeeDtoSecond, employeeDtoThird);
    }

    @Test
    void findAllEmployees() {
        doReturn(employeeDtoList).when(employeeRepository).findAll();

        List<EmployeeDto> list = employeeService.findAll();

        assertAll(
                () -> assertThat(list).hasSize(3).extracting(EmployeeDto::getFirstname)
                        .containsOnly(FIRST_EMPLOYEE_NAME, SECOND_EMPLOYEE_NAME, THIRD_EMPLOYEE_NAME),
                () -> assertThat(list).hasSize(3).extracting(EmployeeDto::getLastname)
                        .containsOnly(FIRST_EMPLOYEE_LAST_NAME, SECOND_EMPLOYEE_LAST_NAME, THIRD_EMPLOYEE_LAST_NAME),
                () -> assertThat(list).hasSize(3).extracting(EmployeeDto::getIsDismissed)
                        .containsOnly(FIRST_EMPLOYEE_IS_DISMISSED, SECOND_EMPLOYEE_IS_DISMISSED, SECOND_EMPLOYEE_IS_DISMISSED)
        );
    }

    @Test
    void findById() {
        when(employeeRepository.findById(FIRST_EMPLOYEE_ID)).thenReturn(employeeDtoFirst);

        EmployeeDto maybeEmployee = employeeService.findById(FIRST_EMPLOYEE_ID);
        verify(employeeRepository, times(1)).findById(anyLong());
        assertNotNull(maybeEmployee);
    }

    @Test
    void findByLastName() {
        when(employeeRepository.findByLastName(THIRD_EMPLOYEE_LAST_NAME)).thenReturn(employeeDtoThird);

        EmployeeDto maybeEmployee = employeeService.findByLastName(THIRD_EMPLOYEE_LAST_NAME);
        verify(employeeRepository, times(1)).findByLastName(THIRD_EMPLOYEE_LAST_NAME);
        assertEquals(maybeEmployee, employeeDtoThird);
    }

    @Test
    void saveEmployee() {
        when(employeeRepository.save(employeeDtoFirst)).thenReturn(employeeDtoFirst);

        EmployeeDto maybeEmployee = employeeService.save(employeeDtoFirst);
        assertNotNull(maybeEmployee);
        assertThat(maybeEmployee).isEqualTo(employeeDtoFirst);
        verify(employeeRepository, times(1)).save(employeeDtoFirst);
    }

    @Test
    void updateEmployee() {
        when(employeeRepository.findById(SECOND_EMPLOYEE_ID)).thenReturn(employeeDtoSecond);
        when(employeeRepository.update(employeeDtoSecond)).thenReturn(employeeDtoUpdated);

        EmployeeDto maybeEmployee = employeeService.findById(SECOND_EMPLOYEE_ID);
        verify(employeeRepository, times(1)).findById(anyLong());
        assertNotNull(maybeEmployee);

        EmployeeDto maybeEmployeeDtoUpdated = employeeService.update(employeeDtoSecond);
        verify(employeeRepository, times(1)).update(employeeDtoSecond);
        assertThat(maybeEmployeeDtoUpdated).isEqualTo(employeeDtoUpdated);
    }

    @Test
    void deleteEmployee() {
        when(employeeRepository.findById(DISMISSED_EMPLOYEE_ID)).thenReturn(employeeDtoDismissed);

        EmployeeDto maybeEmployee = employeeService.findById(DISMISSED_EMPLOYEE_ID);
        verify(employeeRepository, times(1)).findById(anyLong());
        assertNotNull(maybeEmployee);

        employeeService.deleteById(DISMISSED_EMPLOYEE_ID);
        verify(employeeRepository, times(1)).deleteById(DISMISSED_EMPLOYEE_ID);
    }
}

