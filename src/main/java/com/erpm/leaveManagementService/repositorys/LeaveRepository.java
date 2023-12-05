package com.erpm.leaveManagementService.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.erpm.leaveManagementService.entitys.LeaveRequest;

@Repository
@EnableJpaRepositories
public interface LeaveRepository extends JpaRepository<LeaveRequest, Integer> {

	public List<LeaveRequest> findByEmployeeId(int employeeId);

	public List<LeaveRequest> findByApproverId(int approverId);
}
