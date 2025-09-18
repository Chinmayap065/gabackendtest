package com.mtd.GamingArcade.repository;

import com.mtd.GamingArcade.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

// This "extends MongoRepository<Game, String>" part is the key.
public interface GameRepository extends MongoRepository<Game, String> {}