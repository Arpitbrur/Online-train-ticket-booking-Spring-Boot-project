package com.irctc.onlinetrainticketbooking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.irctc.onlinetrainticketbooking.dto.Train;
import com.irctc.onlinetrainticketbooking.repository.TrainRepository;

@Repository
public class TrainDao {

	@Autowired
	private TrainRepository trainRepository;

	
	 // insert Train Details once the ADMIN logged in
	 public Train insertTrain(Train train) {
		 return trainRepository.save(train);
	 }
	
}
