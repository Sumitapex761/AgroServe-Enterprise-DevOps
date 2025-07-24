package com.agro.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.backend.entities.AgroUser;

public interface AgroUserRepository extends JpaRepository<AgroUser, Long> {

}
