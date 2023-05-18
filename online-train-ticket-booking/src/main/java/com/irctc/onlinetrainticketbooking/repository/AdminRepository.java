package com.irctc.onlinetrainticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.irctc.onlinetrainticketbooking.dto.Admin;

@EnableJpaRepositories
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin getByAdminName(String adminName);
}
