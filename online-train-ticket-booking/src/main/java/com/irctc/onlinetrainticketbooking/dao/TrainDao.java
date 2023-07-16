package com.irctc.onlinetrainticketbooking.dao;

import java.util.List;
import java.util.Optional;

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
	
	// update Train by train number
	public Train updateTrain(Train train, int trainNumber) {
		Optional<Train> optional = trainRepository.findById(trainNumber);
		
		if(optional.isPresent()) {
			Train train2 =optional.get();
			if((train.getTrainName()!= null) && (train.getTrainSource()!=null) && (train.getTrainDestination()!=null)){
				train2.setTrainName(train.getTrainName());
				train2.setTrainSource(train.getTrainSource());
				train2.setTrainDestination(train.getTrainDestination());
				
				trainRepository.save(train2);
			}else if((train.getTrainName()!= null) && (train.getTrainSource()!=null)){
				train2.setTrainName(train.getTrainName());
				train2.setTrainSource(train.getTrainSource());
				trainRepository.save(train2);

			}else{
				train2.setTrainName(train.getTrainName());
				trainRepository.save(train2);

			}
		}
		return null;
	}
		
	
	// delete train by trainNumber
	 public String deleteTrain(int trainNumber) {
		 Optional<Train> optional = trainRepository.findById(trainNumber);
		 
		 if(optional.isPresent()) {
			 trainRepository.delete(optional.get());
			 return "train delete Successfully";
		 }else {
			 return "given id is not present in database";
		 }
	 }
	 
	 
	// display all train details		 
	public List<Train> getAllTrainDetails(){
		return trainRepository.findAll();
	}
}
