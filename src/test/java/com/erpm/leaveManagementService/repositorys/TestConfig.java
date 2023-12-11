package com.erpm.leaveManagementService.repositorys;

import javax.sql.DataSource;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig{

	@Bean
	@Primary
	public DataSource dataSource() {
		return new org.h2.jdbcx.JdbcDataSource();
	}

}
