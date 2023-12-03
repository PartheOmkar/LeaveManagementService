package com.erpm.leaveManagementService.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.erpm.leaveManagementService.entitys.LeaveRequest;

@Repository
@EnableJpaRepositories
public interface LeaveRepository extends JpaRepository<LeaveRequest,Integer>{

}
