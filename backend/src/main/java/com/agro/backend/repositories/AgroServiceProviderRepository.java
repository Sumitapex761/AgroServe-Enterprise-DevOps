package com.agro.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.backend.entities.AgroServiceProvider;

public interface AgroServiceProviderRepository extends JpaRepository<AgroServiceProvider, Long> {

	Optional<AgroServiceProvider> findByEmail(String email);

}
