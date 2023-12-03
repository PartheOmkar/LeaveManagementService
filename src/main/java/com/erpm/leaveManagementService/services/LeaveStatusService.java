package com.erpm.leaveManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.erpm.leaveManagementService.entitys.LeaveStatus;
import com.erpm.leaveManagementService.repositorys.LeaveStatusRepository;

public class LeaveStatusService {

	@Autowired
	LeaveStatusRepository leaveStatusRepository;

	public LeaveStatus addLeaveStatus(LeaveStatus leaveStatus) {
		return leaveStatusRepository.save(leaveStatus);
	}

	public LeaveStatus getLeaveStatusById(int leaveStatusId) {
		LeaveStatus leaveStatus=null;
		try {
			leaveStatus = leaveStatusRepository.findById(leaveStatusId).get();
		} catch (NoSuchElementException ex) {
			System.out.println("there is no such element");
		}
		return leaveStatus;
	}

	public List<LeaveStatus> getAllLeaveStatus() {
		return leaveStatusRepository.findAll();
	}

	public void deleteLeaveStatus(int leaveStatusId) {
		leaveStatusRepository.deleteById(leaveStatusId);
	}

}
