package com.irctc.onlinetrainticketbooking.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.irctc.onlinetrainticketbooking.dto.Admin;
import com.irctc.onlinetrainticketbooking.repository.AdminRepository;

@Repository
public class AdminDao {

	 //adminDao is responsible for all the crud operation of admin class
	
	@Autowired
	private AdminRepository adminRepository;
	
	// registerAdmin methods where i will register Admin details in tables
	
	public Admin registerAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	// login Admin with adminName and Password
	public Admin loginWithAdmin(String adminName) {		
		return adminRepository.getByAdminName(adminName);
	}
}


