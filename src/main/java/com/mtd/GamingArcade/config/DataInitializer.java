package com.mtd.GamingArcade.config;

import com.mtd.GamingArcade.entity.AdminUser;
import com.mtd.GamingArcade.repository.AdminUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if the admin user already exists
        if (adminUserRepository.findByUsername("admin").isEmpty()) {
            AdminUser admin = new AdminUser();
            admin.setUsername("admin");
            // The plain text password is "password"
            admin.setPassword(passwordEncoder.encode("password")); 
            adminUserRepository.save(admin);
            System.out.println(">>> Created default admin user with password 'password'");
        }
    }
}