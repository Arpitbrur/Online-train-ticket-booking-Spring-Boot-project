package com.irctc.onlinetrainticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.onlinetrainticketbooking.dto.Train;

public interface TrainRepository extends JpaRepository<Train, Integer>{

}
