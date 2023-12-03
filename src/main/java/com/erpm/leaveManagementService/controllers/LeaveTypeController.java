package com.erpm.leaveManagementService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.erpm.leaveManagementService.entitys.LeaveType;
import com.erpm.leaveManagementService.exceptions.LeaveTypeNotFound;
import com.erpm.leaveManagementService.services.LeaveTypeService;

public class LeaveTypeController {

	@Autowired
	LeaveTypeService leaveTypeService;

	@PostMapping
	public ResponseEntity<LeaveType> addLeaveType(@RequestBody LeaveType newleaveType) {
		LeaveType leaveType = leaveTypeService.addLeaveType(newleaveType);
		return ResponseEntity.ok(leaveType);
	}

	@GetMapping("/{leaveTypeId}")
	public ResponseEntity<LeaveType> getLeaveType(@PathVariable int leaveTypeId) throws LeaveTypeNotFound {
		LeaveType leaveType = leaveTypeService.getLeaveType(leaveTypeId);
		return ResponseEntity.ok(leaveType);
	}

	@GetMapping
	public ResponseEntity<List<LeaveType>> getLeaveType() {
		List<LeaveType> leaveTypes = leaveTypeService.getLeaveType();
		return ResponseEntity.ok(leaveTypes);
	}

	@DeleteMapping("/{leaveTypeId}")
	public ResponseEntity<String> deleteLeaveType(@PathVariable int leaveTypeId) {
		leaveTypeService.deleteLeaveType(leaveTypeId);
		return ResponseEntity.ok("");
	}
}
