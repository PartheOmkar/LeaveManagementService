package com.erpm.leaveManagementService.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erpm.leaveManagementService.exceptions.LeaveTypeNotFound;

@RestControllerAdvice
public class LeaveTypeAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(LeaveTypeNotFound.class)
	public String leaveTypeNotFound(LeaveTypeNotFound ex) {
		return ex.getMessage();
	}
}
