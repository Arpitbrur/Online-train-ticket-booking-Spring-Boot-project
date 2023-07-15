package com.irctc.onlinetrainticketbooking.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.irctc.onlinetrainticketbooking.dao.AdminDao;
import com.irctc.onlinetrainticketbooking.dto.Admin;
import com.irctc.onlinetrainticketbooking.repsonse.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ResponseStructure<Admin> responseStructure;

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
	
	// delete Admin with admin Id
	public String deleteAdmin(int adminId) {
		return adminDao.deleteAdmin(adminId);
	}	
	
	
	// login Admin with adminName and Password
		 
	public ResponseStructure<Admin> loginWithAdmin(String adminName,String adminPassword) {
		
		Admin admin=adminDao.loginWithAdmin(adminName);
		
		if(admin!=null) {
			if((admin.getAdminName().equals(adminName))
					&&(admin.getAdminPassword().equals(adminPassword))) {
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setMsg("Admin----LoggedIn----Successfully...");
				responseStructure.setDescription("once admin logged in he can able to add train details ...");
				responseStructure.setData(admin);
				return responseStructure;
				
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMsg("Admin----LoggedIn----Failed...");
				responseStructure.setDescription("idiot check your username and password...");
				responseStructure.setData(admin);
				return responseStructure;
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMsg("Admin----Details not found...");
			responseStructure.setDescription("Given username is not avaialable in database");
			responseStructure.setData(admin);
			return responseStructure;
		}
	}
}
