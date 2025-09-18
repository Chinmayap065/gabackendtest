package com.mtd.GamingArcade.repository;

import com.mtd.GamingArcade.entity.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface AdminUserRepository extends MongoRepository<AdminUser, String> {
    Optional<AdminUser> findByUsername(String username);
}