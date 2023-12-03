package com.erpm.leaveManagementService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpm.leaveManagementService.entitys.LeaveRequest;
import com.erpm.leaveManagementService.exceptions.LeaveRequestNotFound;
import com.erpm.leaveManagementService.services.LeaveService;

@RestController
@RequestMapping("leave")
public class LeaveController {

	@Autowired
	LeaveService leaveService;

	@PostMapping
	public ResponseEntity<LeaveRequest> leaveRequest(@RequestBody LeaveRequest leaveRequest) {
		LeaveRequest savedLeaveRequest = leaveService.leaveRequest(leaveRequest);
		return ResponseEntity.ok(savedLeaveRequest);
	}

	@GetMapping("/{leaveId}")
	public ResponseEntity<LeaveRequest> getLeaveRequest(@PathVariable int leaveId) throws LeaveRequestNotFound {
		LeaveRequest leaveRequest = leaveService.getLeaveRequest(leaveId);
		return ResponseEntity.ok(leaveRequest);
	}

	@GetMapping
	public ResponseEntity<List<LeaveRequest>> getLeaveRequests() {
		List<LeaveRequest> leaveRequests = leaveService.getLeaveRequests();
		if (leaveRequests == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(leaveRequests);
	}
	
	@PatchMapping
	public ResponseEntity<LeaveRequest> updateLeaveRequest(@RequestBody LeaveRequest leaveRequest) throws LeaveRequestNotFound{
		LeaveRequest updatedleaveRequest=leaveService.updateLeaveRequest(leaveRequest);
		if(updatedleaveRequest==null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updatedleaveRequest);
	}
	
	@DeleteMapping("/{leaveId}")
	public ResponseEntity<String> deleteLeaveRequest(@PathVariable int leaveId){
		leaveService.deleteLeaveRequest(leaveId);
		return ResponseEntity.ok("");
	}
}