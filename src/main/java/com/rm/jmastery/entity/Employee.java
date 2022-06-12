package com.rm.jmastery.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    private Long id;

    private String firstname;

    private String lastname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String city;

    private Gender gender;

    private Boolean isDismissed = Boolean.FALSE;
}
