package com.irctc.onlinetrainticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.irctc.onlinetrainticketbooking.dto.Train;
import com.irctc.onlinetrainticketbooking.repsonse.ResponseStructure;
import com.irctc.onlinetrainticketbooking.service.TrainService;

@RestController
public class TrainController {

	@Autowired
	private TrainService trainService;
	
	// insert Train Details once the ADMIN logged in
	@PostMapping("/saveTrain")
		public ResponseStructure<Train> insertTrain(@RequestBody Train train) {
			return trainService.insertTrain(train);
		}
	
	// update Train by train number
	@PutMapping("/updateTrain/{trainNumber}")
	public ResponseStructure<Train> updateTrain(@RequestBody Train train,@PathVariable int trainNumber) {
		return trainService.updateTrain(train, trainNumber);
	}	
	
	
	// delete train by trainNumber
	@DeleteMapping("/deleteTrain/{trainNumber}")
	public String deleteTrain(@PathVariable int trainNumber) {
		return trainService.deleteTrain(trainNumber);
	}	
			 
			 
	// display all train details	
	@GetMapping("/trainSearch/{trainSource}/{trainDestination}")
	public List<Train> getTrainSourceToDestination(@PathVariable String trainSource, @PathVariable String trainDestination){
	
		return trainService.getTrainSourceToDesination(trainSource, trainDestination);
	}
}
