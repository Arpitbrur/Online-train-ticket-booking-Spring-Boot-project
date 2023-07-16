package com.irctc.onlinetrainticketbooking.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.irctc.onlinetrainticketbooking.dao.UserDao;
import com.irctc.onlinetrainticketbooking.dto.User;
import com.irctc.onlinetrainticketbooking.repsonse.ResponseStructure;

import jakarta.servlet.http.HttpSession;


@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ResponseStructure<User> responseStructure; 

	@Autowired
	private HttpSession httpSession;

	
	// insert method for user-----------------------------------------------------------------
	public ResponseStructure<User> insertUser(User user) {
		
			if((user.getUserName().length()<=10) && (user.getUserPassword().length()<=8)) {
				
				String Password = user.getUserPassword();
				
				Matcher alphabets = Pattern.compile("[a-zA-z]").matcher(Password);
				Matcher special = Pattern.compile("[!@#$%&*]").matcher(Password);
				Matcher digit = Pattern.compile("[0-9]").matcher(Password);
				
				if((alphabets.find()) && (special.find()) && (digit.find())) {
					User user1= userDao.insertUser(user);
					responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
					responseStructure.setMsg("User----Registered----Successfully...");
					responseStructure.setDescription("user has combination of specialcharacter, digits....and alphabets ...");
					responseStructure.setData(user1);
					return responseStructure;
					
				}else {
					responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
					responseStructure.setMsg("User----Not------Registered");
					responseStructure.setDescription("user should has combination of specialcharacter, digits....and alphabets ...");
					responseStructure.setData(user);
					return responseStructure;
				}
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setMsg("User----Not------Registered");
				responseStructure.setDescription("user should has username with less than equals to 10 character and "
											+"password should be less than equals to 8 characters with combination of specialcharacter, digits and alphabets");
				responseStructure.setData(user);
				return responseStructure;
			}

	}
	
	// delete method for user--------------------------------------------------------------
	public String deleteUser(int userId) {
		return userDao.deleteUser(userId);
	}
	
	// User login--------------------------------------------------------------------------
		public ResponseStructure<User> loginUser(String userName, String userPassword) {
			User user  =userDao.loginUser(userName);	
				
			if(user != null) {
					
				if((user.getUserName().equals(userName)) && (user.getUserPassword().equals(userPassword))) {
						
					httpSession.setAttribute("userpassword", user.getUserPassword());
					responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
					responseStructure.setMsg("User----LoggedIn----Successfully...");
					responseStructure.setDescription("once user logged in he can able to book the ticket ...");
					responseStructure.setData(user);
					return responseStructure;
				}else {
					responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
					responseStructure.setMsg("User----LoggedIn----failed...");
					responseStructure.setDescription("Hey please check your username and password...... ...");
					responseStructure.setData(user);
					return responseStructure;
				}
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setMsg("User----failed ----to-----loggin...");
				responseStructure.setDescription(".......provided username is not registered.....yet....");
				responseStructure.setData(user);
				return responseStructure;
			}
		}
			
	
}
