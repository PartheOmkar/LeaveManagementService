package com.erpm.leaveManagementService.entitys;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String leaveStatus;
	@OneToMany(mappedBy = "status")
	private Set<LeaveRequest> leaveRequests;
}
