package com.irctc.onlinetrainticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}
