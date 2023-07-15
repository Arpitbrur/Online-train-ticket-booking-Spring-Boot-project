package com.irctc.onlinetrainticketbooking.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.irctc.onlinetrainticketbooking.dao.TrainDao;
import com.irctc.onlinetrainticketbooking.dto.Train;
import com.irctc.onlinetrainticketbooking.repsonse.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class TrainService {

	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private ResponseStructure<Train> responseStructure;
	
	// insert Train Details once the ADMIN logged in
	public ResponseStructure<Train> insertTrain(Train train) {
		
		if(httpSession.getAttribute("password") != null) {
			train.setDepartureTime(LocalTime.of(11,30));
			train.setArrivalTime(LocalTime.of(8,20));
			Train train1 = trainDao.insertTrain(train);
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMsg("train details stored successfully in database....");
			responseStructure.setDescription("train details stored where trainnumber is a primary key and etc...");
			responseStructure.setData(train1);
			return responseStructure;
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("please logged in with admin");
			responseStructure.setDescription("please logged in with admin and then try to add the train details...");
			responseStructure.setData(train);
			return responseStructure;
		}
	}
	
	
	// delete train by trainNumber
	public String deleteTrain(int trainNumber) {
		return trainDao.deleteTrain(trainNumber);
	}	
	
	
	// display all train details		 
	public List<Train> getTrainSourceToDesination(String trainSource, String trainDestination){
		List<Train> trains = trainDao.getAllTrainDetails();
		
		List<Train> filterTrains = new ArrayList<Train>();
		
		for (Train train : trains) {
			
			if((train.getTrainSource().equalsIgnoreCase(trainSource)) 
					&& (train.getTrainDestination().equalsIgnoreCase(trainDestination))) {
				
				filterTrains.add(train);
			}
		}
		return filterTrains;
	}
	
}
