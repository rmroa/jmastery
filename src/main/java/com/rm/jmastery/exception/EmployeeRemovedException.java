package com.rm.jmastery.exception;

public class EmployeeRemovedException extends RuntimeException {

    public EmployeeRemovedException() {
    }

    public EmployeeRemovedException(String message) {
        super(message);
    }
}
