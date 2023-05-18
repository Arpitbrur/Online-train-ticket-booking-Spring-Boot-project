package com.irctc.onlinetrainticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.irctc.onlinetrainticketbooking.dto.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

	public User getByUserName(String userName);
}
