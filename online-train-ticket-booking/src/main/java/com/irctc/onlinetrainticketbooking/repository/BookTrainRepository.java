package com.irctc.onlinetrainticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.onlinetrainticketbooking.dto.BookTrain;

public interface BookTrainRepository extends JpaRepository<BookTrain, Long> {

}
