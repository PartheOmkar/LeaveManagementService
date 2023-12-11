package com.erpm.leaveManagementService.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import com.erpm.leaveManagementService.dtos.LeaveRequestDto;
import com.erpm.leaveManagementService.entitys.LeaveRequest;
import com.erpm.leaveManagementService.entitys.LeaveStatus;
import com.erpm.leaveManagementService.entitys.LeaveType;
import com.erpm.leaveManagementService.exceptions.LeaveRequestNotFound;
import com.erpm.leaveManagementService.exceptions.LeaveStatusNotFound;
import com.erpm.leaveManagementService.exceptions.LeaveTypeNotFound;
import com.erpm.leaveManagementService.repositorys.LeaveRepository;

@ExtendWith(MockitoExtension.class)
class LeaveServiceTest {

	@Mock
	private LeaveRepository leaveRepository;
	@Mock
	private LeaveTypeService leaveTypeService;
	@Mock
	private LeaveStatusService leaveStatusService;
	@InjectMocks
	private LeaveService leaveService;
	
	LeaveRequestDto leaveRequestDto;
	LeaveType leaveType;
	LeaveStatus leaveStatus;
	LeaveRequest getLeaveRequest;
	
	@BeforeEach
	void setUp() throws LeaveStatusNotFound, LeaveTypeNotFound {
//		arrange
		leaveRequestDto = new LeaveRequestDto();
		leaveRequestDto.setEmployeeId(1);
		leaveRequestDto.setApproverId(2);
		leaveRequestDto.setLeaveTypeId(1);
		leaveRequestDto.setStatusId(1);
		leaveRequestDto.setAdditionalComments("no comment");
		leaveRequestDto.setReason("nothing");
		leaveRequestDto.setStartDate(null);
		leaveRequestDto.setEndDate(null);
		
		leaveType = new LeaveType();
		leaveType.setLeaveType("PTO");
		when(leaveTypeService.getLeaveType(1)).thenReturn(leaveType);
		
		leaveStatus = new LeaveStatus();
		leaveStatus.setLeaveStatus("pending");
		when(leaveStatusService.getLeaveStatusById(1)).thenReturn(leaveStatus);
		
		getLeaveRequest = leaveService.getLeaveRequest(leaveRequestDto);
	}
	
	@Test
	void leaveRequest_validInput_sucess() throws LeaveTypeNotFound, LeaveStatusNotFound {
//		arrange
		when(leaveRepository.save(any(LeaveRequest.class))).thenReturn(getLeaveRequest);
//		act
		LeaveRequestDto currentLeaDto = leaveService.leaveRequest(leaveRequestDto);
//		assert
		assertNotNull(currentLeaDto);
	}
	
	@Test
	void leaveRequest_NullInput_throwIllegalArgumentException() {
		LeaveRequestDto leaveRequestDto = null;
		when(leaveRepository.save(null)).thenThrow(IllegalArgumentException.class);
		assertThrows(IllegalArgumentException.class,() -> {
			leaveService.leaveRequest(leaveRequestDto);
		});
	}
	/*
	 * when the entity uses optimistic locking and has a version attribute with a 
	 * different value from that found in the persistence store. 
	 * Also thrown if the entity is assumed to be present but does not exist in the database.
	 */
	@Test
	void leaveRequest_EntityIsPresentButDoesNotExistInTheDataBase_throwOptimisticLockingFailureException() {
		
		LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
		assertThrows(OptimisticLockingFailureException.class, ()->{
			when(leaveRepository.save(any())).thenThrow(OptimisticLockingFailureException.class);
			leaveService.leaveRequest(leaveRequestDto);
		});
	}
	
	@Test
	void getLeaveRequest_validInput_sucess() throws LeaveRequestNotFound {
		int leaveId = 1;
		when(leaveRepository.findById(1)).thenReturn(Optional.of(getLeaveRequest));
		LeaveRequestDto leaveRequestDto = leaveService.getLeaveRequest(leaveId);
		assertNotNull(leaveRequestDto);
	}
	
	@Test
	void getLeaveRequest_InvalidInput_ThrownLeaveRequestNotFound() throws LeaveRequestNotFound{
		int leaveId = 2;
		when(leaveRepository.findById(2)).thenReturn(Optional.empty());
		assertThrows(LeaveRequestNotFound.class, ()->{
			leaveService.getLeaveRequest(leaveId);
		});
	}
	
	@Test
	void getLeaveRequests_Sucess() {
		ArrayList<LeaveRequest> oldLeaveRequests = new ArrayList<LeaveRequest>();
		oldLeaveRequests.add(getLeaveRequest);
		when(leaveRepository.findAll()).thenReturn(oldLeaveRequests);
		List<LeaveRequestDto> leaveRequests = leaveService.getLeaveRequests();
		assertNotNull(leaveRequests);
	}
	
	@Test
	void updateLeaveRequest_ValidInput_Sucess() throws LeaveRequestNotFound, LeaveStatusNotFound {
		leaveRequestDto.setStatusId(2);
		LeaveStatus leaveStatus2 = new LeaveStatus();
		leaveStatus2.setLeaveStatus("approve");
		getLeaveRequest.setStatus(leaveStatus2);
		
		when(leaveRepository.findById(anyInt())).thenReturn(Optional.of(getLeaveRequest));
		when(leaveStatusService.getLeaveStatusById(2)).thenReturn(leaveStatus2);
		when(leaveRepository.save(any(LeaveRequest.class))).thenReturn(getLeaveRequest);
		LeaveRequestDto leaveDto = leaveService.updateLeaveRequest(leaveRequestDto);
		
		assertEquals(leaveStatus2.getId(),leaveDto.getStatusId());
	}
	
	@Test
	void updateLeaveRequest_InvalidLeaveREquestId_ThrowNoSuchElementException() throws LeaveStatusNotFound {
		leaveRequestDto.setStatusId(2);
		LeaveStatus leaveStatus2 = new LeaveStatus();
		leaveStatus2.setLeaveStatus("approve");
		getLeaveRequest.setStatus(leaveStatus2);

		when(leaveRepository.findById(anyInt())).thenReturn(Optional.empty());
		
		assertThrows(LeaveRequestNotFound.class, ()->{
			leaveService.updateLeaveRequest(leaveRequestDto);
		});
	}
	
	@Test
	void getLeaveRequestByEmployeeId_sucess() {
		List<LeaveRequest> oldLeaveRequests = new ArrayList<LeaveRequest>();
		oldLeaveRequests.add(getLeaveRequest);
		when(leaveRepository.findByEmployeeId(1)).thenReturn(oldLeaveRequests);
		List<LeaveRequestDto> leaves = leaveService.getLeaveRequestByEmployeeId(1);
		assertNotNull(leaves);
		assertEquals(oldLeaveRequests.get(0).getEmployeeId(),leaves.get(0).getEmployeeId());
	}
	
	@Test
	void getLeaveRequestByApprovalId_Sucess() {
		List<LeaveRequest> oldLeaveRequests = new ArrayList<LeaveRequest>();
		oldLeaveRequests.add(getLeaveRequest);
		when(leaveRepository.findByApproverId(anyInt())).thenReturn(oldLeaveRequests);
		List<LeaveRequestDto> leaves = leaveService.getLeaveRequestByApprovalId(1);
		assertNotNull(leaves);
		assertEquals(oldLeaveRequests.get(0).getApproverId(),leaves.get(0).getApproverId());
	}
	
	@Test
	void deleteLeaveRequest_Sucess() {
		int leaveId = 1;
		leaveService.deleteLeaveRequest(leaveId);
		verify(leaveRepository).deleteById(leaveId);
	}
}
