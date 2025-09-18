package com.mtd.GamingArcade.service;

import com.mtd.GamingArcade.entity.AdminUser;
import com.mtd.GamingArcade.repository.AdminUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminUserRepository adminUserRepository;

    public CustomUserDetailsService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser adminUser = adminUserRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Admin user not found: " + username));

        return new User(adminUser.getUsername(), adminUser.getPassword(), new ArrayList<>());
    }
}