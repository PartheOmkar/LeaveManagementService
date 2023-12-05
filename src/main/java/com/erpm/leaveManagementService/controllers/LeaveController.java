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

import com.erpm.leaveManagementService.dtos.LeaveRequestDto;
import com.erpm.leaveManagementService.exceptions.LeaveRequestNotFound;
import com.erpm.leaveManagementService.exceptions.LeaveStatusNotFound;
import com.erpm.leaveManagementService.exceptions.LeaveTypeNotFound;
import com.erpm.leaveManagementService.services.LeaveService;

@RestController
@RequestMapping("leave")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@PostMapping
	public ResponseEntity<LeaveRequestDto> leaveRequest(@RequestBody LeaveRequestDto leaveRequest)
			throws LeaveTypeNotFound, LeaveStatusNotFound {
		LeaveRequestDto savedLeaveRequest = leaveService.leaveRequest(leaveRequest);
		return ResponseEntity.ok(savedLeaveRequest);
	}

	@GetMapping("/{leaveId}")
	public ResponseEntity<LeaveRequestDto> getLeaveRequest(@PathVariable int leaveId) throws LeaveRequestNotFound {
		LeaveRequestDto leaveRequest = leaveService.getLeaveRequest(leaveId);
		return ResponseEntity.ok(leaveRequest);
	}

	@GetMapping
	public ResponseEntity<List<LeaveRequestDto>> getLeaveRequests() {
		List<LeaveRequestDto> leaveRequests = leaveService.getLeaveRequests();
		if (leaveRequests == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(leaveRequests);
	}

	@PatchMapping
	public ResponseEntity<LeaveRequestDto> updateLeaveRequest(@RequestBody LeaveRequestDto leaveRequest)
			throws LeaveRequestNotFound, LeaveStatusNotFound {
		LeaveRequestDto updatedleaveRequest = leaveService.updateLeaveRequest(leaveRequest);
		if (updatedleaveRequest == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updatedleaveRequest);
	}

	@DeleteMapping("/{leaveId}")
	public ResponseEntity<String> deleteLeaveRequest(@PathVariable int leaveId) {
		leaveService.deleteLeaveRequest(leaveId);
		return ResponseEntity.ok("");
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<LeaveRequestDto>> getLeaveRequestByEmployeeId(@PathVariable int employeeId) {
		List<LeaveRequestDto> leaves = leaveService.getLeaveRequestByEmployeeId(employeeId);
		return ResponseEntity.ok(leaves);
	}

	@GetMapping("/approver/{approvarId}")
	public ResponseEntity<List<LeaveRequestDto>> getLeaveRequestByApprovalId(@PathVariable int approvarId) {
		List<LeaveRequestDto> leaves = leaveService.getLeaveRequestByApprovalId(approvarId);
		return ResponseEntity.ok(leaves);
	}
}
