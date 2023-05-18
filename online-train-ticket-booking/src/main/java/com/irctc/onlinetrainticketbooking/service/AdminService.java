package com.irctc.onlinetrainticketbooking.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.onlinetrainticketbooking.dao.AdminDao;
import com.irctc.onlinetrainticketbooking.dto.Admin;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private HttpSession httpSession;

	// register admin methods where i will register admin details in tables
	
	public Admin registerAdmin(Admin admin) {
		String password = admin.getAdminPassword();
		
		if(password.length() == 8) {
			Pattern alphabets = Pattern.compile("[a-zA-Z]");
			Pattern numbers = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[!@#$*&%]");
			
			Matcher alpha = alphabets.matcher(password);
			Matcher num = numbers.matcher(password);
			Matcher spec = special.matcher(password);
			
			if((alpha.find()) && (num.find()) && (spec.find())){
				
				adminDao.registerAdmin(admin);
				return admin;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	
	// login Admin with adminName and Password
		 
	public Admin loginWithAdmin(String adminName,String adminPassword) {
		
		Admin admin=adminDao.loginWithAdmin(adminName);
		
		if(admin != null) {
			if((admin.getAdminName().equalsIgnoreCase(adminName))
					&&(admin.getAdminPassword().contentEquals(adminPassword))) {
				httpSession.setAttribute("username", admin.getAdminName());
				return admin;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
}
