package com.irctc.onlinetrainticketbooking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.irctc.onlinetrainticketbooking.dto.BookTrain;
import com.irctc.onlinetrainticketbooking.repository.BookTrainRepository;

@Repository
public class BookTrainDao {

	@Autowired
	private BookTrainRepository bookTrainRepository;
	
	// BookTrain Method----------------------------------------------------------
	public BookTrain insertBookTrain(BookTrain bookTrain) {
		return bookTrainRepository.save(bookTrain);
	}
	
}
