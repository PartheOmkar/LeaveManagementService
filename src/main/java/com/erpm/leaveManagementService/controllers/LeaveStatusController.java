package com.erpm.leaveManagementService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.erpm.leaveManagementService.services.LeaveStatusService;

@RestController
public class LeaveStatusController {

	@Autowired
	LeaveStatusService leaveStatusService;
	
	
}
