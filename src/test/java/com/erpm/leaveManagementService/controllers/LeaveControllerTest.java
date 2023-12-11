package com.erpm.leaveManagementService.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.erpm.leaveManagementService.dtos.LeaveRequestDto;
import com.erpm.leaveManagementService.services.LeaveService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LeaveController.class)
class LeaveControllerTest {

	@MockBean
	private LeaveService leaveService;
	@Autowired
	private MockMvc mockMvc;
	private LeaveRequestDto leaveRequestDto;
	
	@BeforeEach
	public void setUp() {
		leaveRequestDto = new LeaveRequestDto();
		leaveRequestDto.setEmployeeId(1);
		leaveRequestDto.setApproverId(2);
		leaveRequestDto.setLeaveTypeId(1);
		leaveRequestDto.setStatusId(1);
		leaveRequestDto.setAdditionalComments("no comment");
		leaveRequestDto.setReason("nothing");
		leaveRequestDto.setStartDate(null);
		leaveRequestDto.setEndDate(null);
	}
	
	@Test
	public void leaveRequest_ValidInput_Sucess() throws Exception {
//		arrange
		when(leaveService.leaveRequest(any(LeaveRequestDto.class))).thenReturn(leaveRequestDto);
		ObjectMapper objectMapper = new ObjectMapper();
		String leaveReuestJson = objectMapper.writeValueAsString(leaveRequestDto);
//		act and assert
		mockMvc.perform(post("/leave")
				.contentType(MediaType.APPLICATION_JSON)
				.content(leaveReuestJson)
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.statusId").value(1));
	}
	
	@Test
	public void getLeaveRequest_ValidInput_Sucess() throws Exception {
		when(leaveService.getLeaveRequest(anyInt())).thenReturn(leaveRequestDto);
		int leaveId = 1;
		mockMvc.perform(get("/leave/{leaveId}",leaveId))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.employeeId").value(1))
		.andExpect(jsonPath("$.approverId").value(2));
	}
	
	@Test
	public void getLeaveRequests_validInput_Sucess() throws Exception {
		List<LeaveRequestDto> leaveRequestDtos = new ArrayList<LeaveRequestDto>();
		leaveRequestDtos.add(leaveRequestDto);
		when(leaveService.getLeaveRequests()).thenReturn(leaveRequestDtos);
		
		mockMvc.perform(get("/leave"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void updateLeaveRequest_validInput_Sucess() throws Exception {
		when(leaveService.updateLeaveRequest(any(LeaveRequestDto.class))).thenReturn(leaveRequestDto);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String leaveRequestJson = objectMapper.writeValueAsString(leaveRequestDto);
		
		mockMvc.perform(patch("/leave")
				.contentType(MediaType.APPLICATION_JSON)
				.content(leaveRequestJson))
		.andExpect(status().isOk());
	}
	
	@Test
	public void deleteLeaveRequest_validInput_Sucess() throws Exception {
		doNothing().when(leaveService).deleteLeaveRequest(anyInt());
		
		mockMvc.perform(delete("/leave/{leaveId}",1))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getLeaveRequestByEmployeeId_validInput_Sucess() throws Exception {
		List<LeaveRequestDto> leaves = new ArrayList<LeaveRequestDto>();
		leaves.add(leaveRequestDto);
		when(leaveService.getLeaveRequestByEmployeeId(anyInt())).thenReturn(leaves);
		
		mockMvc.perform(get("/leave/employee/{employeeId}",1))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getLeaveRequestByApprovalId_validInput_Sucess() throws Exception {
		List<LeaveRequestDto> leaves = new ArrayList<LeaveRequestDto>();
		leaves.add(leaveRequestDto);
		when(leaveService.getLeaveRequestByApprovalId(anyInt())).thenReturn(leaves);
	
		mockMvc.perform(get("/leave/approver/{approvarId}",1))
		.andExpect(status().isOk());
	}
}
