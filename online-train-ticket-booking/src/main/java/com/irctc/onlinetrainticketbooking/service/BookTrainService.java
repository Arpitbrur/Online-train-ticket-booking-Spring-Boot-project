package com.irctc.onlinetrainticketbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.irctc.onlinetrainticketbooking.dao.BookTrainDao;
import com.irctc.onlinetrainticketbooking.dto.BookTrain;
import com.irctc.onlinetrainticketbooking.dto.Train;
import com.irctc.onlinetrainticketbooking.repsonse.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class BookTrainService {

	@Autowired
	private BookTrainDao bookTrainDao;
	
	@Autowired
	private TrainService trainService;
	
	@Autowired
	private ResponseStructure<BookTrain> responseStructure;
	
	@Autowired
	private HttpSession httpSession;
	
	// BookTrain Method----------------------------------------------------------------------------
	public ResponseStructure<BookTrain> insertBookTrain(BookTrain bookTrain, String trainSource,String trainDestination,int trainNumber) {

		List<Train> trains = trainService.getTrainSourceToDesination(trainSource, trainDestination);
		
		if(httpSession.getAttribute("userpassword") != null) {
			for (Train train : trains) {
				
				if((train.getTrainSource().equalsIgnoreCase(trainSource))					
						&&(train.getTrainDestination().equalsIgnoreCase(trainDestination))
						&&(train.getTrainNumber()==trainNumber)) {
					
					long pnr = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;

					bookTrain.setArrivelTime(train.getArrivalTime());
					bookTrain.setDepartureTime(train.getDepartureTime());
					bookTrain.setDestination(train.getTrainDestination());
					bookTrain.setSource(train.getTrainSource());
					bookTrain.setTrainName(train.getTrainName());
					bookTrain.setSeatNumber(345);
					bookTrain.setTrainNumber(train.getTrainNumber());
					bookTrain.setPnrNumber(pnr);
					
				 bookTrainDao.insertBookTrain(bookTrain);
					
					if(bookTrain != null) {
						responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
						responseStructure.setMsg("----------Train-Ticket-Booking-Successfully--------");
						responseStructure.setDescription("train ticket booked please check your mail we send your ticket pdf on your mail");
						responseStructure.setData(bookTrain);
						
					}else {
						responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
						responseStructure.setMsg("Train-Ticket--Booking---Failed");
						responseStructure.setDescription("train ticket booking failed please check your passed data");
						responseStructure.setData(bookTrain);
						
					}
				}else {
					responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
					responseStructure.setMsg("Train number is not correct....or source and detination are not correct");
					responseStructure.setDescription("please provide correct source and destination....or train is not running on provided source destination");
					responseStructure.setData(bookTrain);
				}
				
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("Please login with user and then booked the train");
			responseStructure.setDescription("without user logoin you cant book ticket");
			responseStructure.setData(bookTrain);
		}
		return responseStructure;
	}	
}
