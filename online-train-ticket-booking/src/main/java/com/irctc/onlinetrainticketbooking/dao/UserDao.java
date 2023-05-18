package com.irctc.onlinetrainticketbooking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.irctc.onlinetrainticketbooking.dto.User;
import com.irctc.onlinetrainticketbooking.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;
	
	// insert method for user------------------------------------------------------------
	public User insertUser(User user) {
		return userRepository.save(user);
	}
	
	

}
