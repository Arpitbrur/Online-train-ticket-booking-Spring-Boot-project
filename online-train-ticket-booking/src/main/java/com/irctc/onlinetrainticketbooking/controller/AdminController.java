package com.irctc.onlinetrainticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.irctc.onlinetrainticketbooking.dto.Admin;
import com.irctc.onlinetrainticketbooking.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	// register admin methods where i will register admin details in tables
	@PostMapping("/registerAdmin")
	public Admin registerAdmin(@RequestBody Admin admin) {
		Admin admin2 = adminService.registerAdmin(admin);
		
		if(admin2 !=null) {
			return admin;
		}else {
			return null;
		}
	}
	
	// login Admin with adminName and Password
	@GetMapping("/loginAdmin/{adminName}/{adminPassword}")
	public Admin loginWithAdmin(@PathVariable String adminName,@PathVariable String adminPassword) {
		Admin admin = adminService.loginWithAdmin(adminName, adminPassword);
			
		if(admin != null) {
			return admin;
		}else {
			return null;
	    }
    }
	
}
