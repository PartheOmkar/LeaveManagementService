package com.erpm.leaveManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpm.leaveManagementService.entitys.LeaveType;
import com.erpm.leaveManagementService.exceptions.LeaveTypeNotFound;
import com.erpm.leaveManagementService.repositorys.LeaveTypeRepository;

@Service
public class LeaveTypeService {

	@Autowired
	LeaveTypeRepository leaveTypeRepository;

	public LeaveType addLeaveType(LeaveType newleaveType) {
		return leaveTypeRepository.save(newleaveType);
	}

	public LeaveType getLeaveType(int leaveTypeId) throws LeaveTypeNotFound {
		LeaveType leaveType;
		try {
			leaveType = leaveTypeRepository.findById(leaveTypeId).get();
		}catch (NoSuchElementException e) {
			throw new LeaveTypeNotFound("leave type not found +"+leaveTypeId);
		}
		return leaveType;
	}

	public List<LeaveType> getLeaveType() {
		return leaveTypeRepository.findAll();
	}

	public void deleteLeaveType(int leaveTypeId) {
		leaveTypeRepository.deleteById(leaveTypeId);
		return;
	}

}
