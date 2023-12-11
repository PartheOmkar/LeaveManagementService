package com.erpm.leaveManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.erpm.leaveManagementService.dtos.LeaveRequestDto;
import com.erpm.leaveManagementService.entitys.LeaveRequest;
import com.erpm.leaveManagementService.exceptions.LeaveRequestNotFound;
import com.erpm.leaveManagementService.exceptions.LeaveStatusNotFound;
import com.erpm.leaveManagementService.exceptions.LeaveTypeNotFound;
import com.erpm.leaveManagementService.repositorys.LeaveRepository;

@Service
public class LeaveService {

	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private LeaveTypeService leaveTypeService;
	@Autowired
	private LeaveStatusService leaveStatusService;

	public LeaveRequestDto leaveRequest(LeaveRequestDto newleaveRequest) throws LeaveTypeNotFound, LeaveStatusNotFound {
		LeaveRequestDto leaveRequest = null;
		try {
			LeaveRequest lea = leaveRepository.save(getLeaveRequest(newleaveRequest));
			leaveRequest = new LeaveRequestDto(lea);
		} catch (OptimisticLockingFailureException ex) {
			throw ex;
		} catch (IllegalArgumentException ex) {
			throw ex;
		}
		return leaveRequest;
	}

	public LeaveRequestDto getLeaveRequest(int leaveId) throws LeaveRequestNotFound {
		LeaveRequestDto leaveRequestDto = null;
		try {
			LeaveRequest leaveRequest = leaveRepository.findById(leaveId).get();
			leaveRequestDto = new LeaveRequestDto(leaveRequest);
		} catch (NoSuchElementException ex) {
			throw new LeaveRequestNotFound("leave request id = " + leaveId + " not found");
		}
		return leaveRequestDto;
	}

	public List<LeaveRequestDto> getLeaveRequests() {
		return leaveRepository.findAll().stream().map(leaveRequest -> new LeaveRequestDto(leaveRequest)).toList();
	}

	public LeaveRequestDto updateLeaveRequest(LeaveRequestDto newleaveRequest)
			throws LeaveRequestNotFound, LeaveStatusNotFound {
		LeaveRequestDto leaveRequestDto = null;
		try {
			LeaveRequest leaveRequest = leaveRepository.findById(newleaveRequest.getId()).get();
			leaveRequest.setStatus(leaveStatusService.getLeaveStatusById(newleaveRequest.getStatusId()));
			leaveRequest = leaveRepository.save(leaveRequest);
			leaveRequestDto = new LeaveRequestDto(leaveRequest);
		} catch (NoSuchElementException ex) {
			throw new LeaveRequestNotFound("leave request id = +" + newleaveRequest.getId() + " not found");
		}
		return leaveRequestDto;
	}

	public void deleteLeaveRequest(int leaveId) {
		leaveRepository.deleteById(leaveId);
	}

	public LeaveRequest getLeaveRequest(LeaveRequestDto leaveRequestDto) throws LeaveTypeNotFound, LeaveStatusNotFound {
		if(leaveRequestDto==null) {
			return null;
		}
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest.setEmployeeId(leaveRequestDto.getEmployeeId());
		leaveRequest.setStartDate(leaveRequestDto.getStartDate());
		leaveRequest.setEndDate(leaveRequestDto.getEndDate());
		leaveRequest.setLeaveType(leaveTypeService.getLeaveType(leaveRequestDto.getLeaveTypeId()));
		leaveRequest.setStatus(leaveStatusService.getLeaveStatusById(leaveRequestDto.getStatusId()));
		leaveRequest.setReason(leaveRequestDto.getReason());
		leaveRequest.setAdditionalComments(leaveRequestDto.getAdditionalComments());
		leaveRequest.setApproverId(leaveRequestDto.getApproverId());
		return leaveRequest;
	}

	public List<LeaveRequestDto> getLeaveRequestByEmployeeId(int employeeId) {
		List<LeaveRequestDto> leaves = leaveRepository.findByEmployeeId(employeeId).stream()
				.map(leave -> new LeaveRequestDto(leave)).toList();
		return leaves;
	}

	public List<LeaveRequestDto> getLeaveRequestByApprovalId(int approvarId) {
		List<LeaveRequestDto> leaves = leaveRepository.findByApproverId(approvarId).stream()
				.map(leave -> new LeaveRequestDto(leave)).toList();
		return leaves;
	}
}
