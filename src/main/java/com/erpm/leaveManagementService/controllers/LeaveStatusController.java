package com.erpm.leaveManagementService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpm.leaveManagementService.entitys.LeaveStatus;
import com.erpm.leaveManagementService.services.LeaveStatusService;

@RestController
@RequestMapping("/leaveStatus")
public class LeaveStatusController {

	@Autowired
	LeaveStatusService leaveStatusService;
	
	@PostMapping
	public ResponseEntity<LeaveStatus> addLeaveStatus(@RequestBody LeaveStatus leaveStatus) {
		LeaveStatus savedLeaveStatus = leaveStatusService.addLeaveStatus(leaveStatus);
		return ResponseEntity.ok(savedLeaveStatus);
	}
	
	@GetMapping("/{leaveStatusId}")
	public ResponseEntity<LeaveStatus> getLeaveStatusById(@PathVariable int leaveStatusId){
		LeaveStatus leaveStatus = leaveStatusService.getLeaveStatusById(leaveStatusId);
		return ResponseEntity.ok(leaveStatus);
	}
	
	@GetMapping
	public ResponseEntity<List<LeaveStatus>> getAllLeaveStatus(){
		List<LeaveStatus> leaveStatus = leaveStatusService.getAllLeaveStatus();
		return ResponseEntity.ok(leaveStatus);
	}
	
	@DeleteMapping("/{leaveStatusId}")
	public ResponseEntity<String> deleteLeaveStatus(@PathVariable int leaveStatusId){
		leaveStatusService.deleteLeaveStatus(leaveStatusId);
		return ResponseEntity.ok("");
	}
}
