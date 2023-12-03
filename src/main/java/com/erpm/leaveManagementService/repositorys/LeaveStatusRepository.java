package com.erpm.leaveManagementService.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.erpm.leaveManagementService.entitys.LeaveStatus;

@Repository
@EnableJpaRepositories
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Integer> {

}
