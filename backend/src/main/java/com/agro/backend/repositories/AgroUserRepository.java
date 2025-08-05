package com.agro.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.backend.entities.AgroUser;


public interface AgroUserRepository extends JpaRepository<AgroUser, Long> {
    
	boolean existsByEmail(String email);
	
	Optional<AgroUser> findByEmail(String email);
}
