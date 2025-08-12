package com.agro.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.backend.entities.AgroUser;


public interface AgroUserRepository extends JpaRepository<AgroUser, Long> {
    
	Optional<AgroUser> findByEmailAndPassword(String email, String password);

	// derived finder
	boolean existsByEmail(String email);

	// derived finder
	Optional<AgroUser> findByEmail(String email);

}
