package com.rm.jmastery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rm.jmastery.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private Long id;

    private String firstname;

    private String lastname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String city;

    private Gender gender;

    private Boolean isDismissed = Boolean.FALSE;

    public EmployeeDto(String firstname, String lastname, Boolean isDismissed) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.isDismissed = isDismissed;
    }
}
