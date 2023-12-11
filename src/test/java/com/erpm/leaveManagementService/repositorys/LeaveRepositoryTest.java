package com.erpm.leaveManagementService.repositorys;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.erpm.leaveManagementService.entitys.LeaveRequest;
import com.erpm.leaveManagementService.entitys.LeaveStatus;
import com.erpm.leaveManagementService.entitys.LeaveType;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
class LeaveRepositoryTest {

	@Autowired
	private LeaveRepository leaveRepository;
	private LeaveRequest leaveRequest;
	private LeaveType leaveType;
	private LeaveStatus leaveStatus;

	@BeforeEach
	void setUp() throws Exception {
		leaveType = new LeaveType();
		leaveType.setLeaveType("PTO");
		
		leaveStatus = new LeaveStatus();
		leaveStatus.setLeaveStatus("pending");
		
		leaveRequest = new LeaveRequest();
		leaveRequest.setEmployeeId(1);
		leaveRequest.setApproverId(2);
		leaveRequest.setLeaveType(leaveType);
		leaveRequest.setStatus(leaveStatus);
		leaveRequest.setAdditionalComments("no comment");
		leaveRequest.setReason("nothing");
		leaveRequest.setStartDate(null);
		leaveRequest.setEndDate(null);
	}

	@AfterEach
	void tearDown() throws Exception {
		leaveRequest = null;
		leaveStatus = null;
		leaveType = null;
		leaveRepository.deleteAll();
	}

	@Test
	void findByEmployeeId_find() {
		leaveRepository.save(leaveRequest);
		int employeeId = 1;
		List<LeaveRequest> leaveRequest = leaveRepository.findByEmployeeId(employeeId);
		assertEquals(leaveRequest.get(0).getEmployeeId(), employeeId);
	}
	
	@Test
	void findByApproverId_find() {
		leaveRepository.save(leaveRequest);
		int approverId = 2;
		List<LeaveRequest> leaveRequests = leaveRepository.findByApproverId(approverId);
		assertEquals(leaveRequests.get(0).getApproverId(), approverId);
	}

}
