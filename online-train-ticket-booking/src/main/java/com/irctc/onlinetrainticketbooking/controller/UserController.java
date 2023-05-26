package com.irctc.onlinetrainticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irctc.onlinetrainticketbooking.dto.User;
import com.irctc.onlinetrainticketbooking.repsonse.ResponseStructure;
import com.irctc.onlinetrainticketbooking.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	
	// insert method for user-------------------------------------------------------------
	@PostMapping("/saveUser")
	public ResponseStructure<User> insertUser(@RequestBody User user) {
		return service.insertUser(user);
	}
	
	// User login--------------------------------------------------------------------------
	@GetMapping("/loginUser/{userName}/{userPassword}")
	public ResponseStructure<User> loginUser(@PathVariable String userName, @PathVariable String userPassword) {
				
		return service.loginUser(userName, userPassword);
	}
}
