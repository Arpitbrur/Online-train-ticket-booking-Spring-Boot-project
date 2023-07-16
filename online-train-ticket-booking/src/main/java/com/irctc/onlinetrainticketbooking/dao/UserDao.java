package com.irctc.onlinetrainticketbooking.dao;

import java.util.Optional;

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
	
	// delete method for user--------------------------------------------------------------
	public String deleteUser(int userId) {
		Optional<User> optional = userRepository.findById(userId);
		if(optional.isPresent()) {
			userRepository.delete(optional.get());
			return "user deleted successfully ";
		}else {
			return "given id is not found in database";
		}
	}
	
	// User login--------------------------------------------------------------------------
	public User loginUser(String userName) {
		return userRepository.getByUserName(userName);
	}

}
