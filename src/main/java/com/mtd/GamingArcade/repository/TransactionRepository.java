package com.mtd.GamingArcade.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mtd.GamingArcade.entity.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByMemberIdOrderByDateTimeDesc(String memberId);
    List<Transaction> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    
}