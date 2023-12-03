package com.erpm.leaveManagementService.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erpm.leaveManagementService.exceptions.LeaveStatusNotFound;

@RestControllerAdvice
public class LeaveStatusAdvice {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(LeaveStatusNotFound.class)
	public String leaveStatusNotFound(LeaveStatusNotFound ex) {
		return ex.getMessage();
	}

}
