package com.mtd.GamingArcade.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mtd.GamingArcade.entity.Recharge;

public interface RechargeRepository extends MongoRepository<Recharge, String> {
    List<Recharge> findByMemberId(String memberId);
    List<Recharge> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}