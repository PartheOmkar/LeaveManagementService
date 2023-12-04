package com.erpm.leaveManagementService.dtos;

import java.time.LocalDate;

import com.erpm.leaveManagementService.entitys.LeaveRequest;

public class LeaveRequestDto {

	private int id;
	private int employeeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private int leaveTypeId;
	private int statusId;
	private String reason;
	private String additionalComments;
	private int approverId;
	
	public LeaveRequestDto() {
		
	}
	
	public LeaveRequestDto(LeaveRequest leaveRequest) {
		this.id = leaveRequest.getId();
		this.employeeId = leaveRequest.getEmployeeId();
		this.startDate = leaveRequest.getStartDate();
		this.endDate = leaveRequest.getEndDate();
		this.leaveTypeId = leaveRequest.getLeaveType().getId();
		this.statusId = leaveRequest.getStatus().getId();
		this.reason = leaveRequest.getReason();
		this.additionalComments = leaveRequest.getAdditionalComments();
		this.approverId = leaveRequest.getApproverId();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

}
