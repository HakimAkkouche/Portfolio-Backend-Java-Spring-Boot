package com.hakimsprojects.portfolio.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hakimsprojects.portfolio.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
	Optional<AppUser> findByUsername(String username);
	
}
