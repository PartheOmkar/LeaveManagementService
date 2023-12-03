package com.erpm.leaveManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.erpm.leaveManagementService.entitys.LeaveRequest;
import com.erpm.leaveManagementService.exceptions.LeaveRequestNotFound;
import com.erpm.leaveManagementService.repositorys.LeaveRepository;

@Service
public class LeaveService {

	@Autowired
	LeaveRepository leaveRepository;

	public LeaveRequest leaveRequest(LeaveRequest newleaveRequest) {
		LeaveRequest leaveRequest = null;
		try {
			leaveRequest = leaveRepository.save(newleaveRequest);
		} catch (OptimisticLockingFailureException ex) {
			throw ex;
		} catch (IllegalArgumentException ex) {
			throw ex;
		}
		return leaveRequest;
	}

	public LeaveRequest getLeaveRequest(int leaveId) throws LeaveRequestNotFound {
		LeaveRequest leaveRequest = null;
		try {
			leaveRequest = leaveRepository.findById(leaveId).get();
		} catch (NoSuchElementException ex) {
			throw new LeaveRequestNotFound("leave request id = "+leaveId+" not found");
		}
		return leaveRequest;
	}

	public List<LeaveRequest> getLeaveRequests() {
		return leaveRepository.findAll();
	}

	public LeaveRequest updateLeaveRequest(LeaveRequest newleaveRequest) throws LeaveRequestNotFound {
		LeaveRequest leaveRequest = null;
		try {
			leaveRequest = leaveRepository.findById(newleaveRequest.getId()).get();
		} catch (NoSuchElementException ex) {
			throw new LeaveRequestNotFound("leave request id = +"+newleaveRequest.getId()+" not found");
		}
		leaveRequest.setStartDate(newleaveRequest.getStartDate());
		leaveRequest.setEndDate(newleaveRequest.getEndDate());
		return leaveRequest;
	}

	public void deleteLeaveRequest(int leaveId) {
		leaveRepository.deleteById(leaveId);
	}

}
