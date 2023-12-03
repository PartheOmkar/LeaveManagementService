package com.erpm.leaveManagementService.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erpm.leaveManagementService.exceptions.LeaveRequestNotFound;

@RestControllerAdvice
public class LeaveRequestAdvice {

	@ExceptionHandler(LeaveRequestNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String leaveRequestNotFound(LeaveRequestNotFound lv){
		String message = lv.getMessage();
		return message;
	}
}
