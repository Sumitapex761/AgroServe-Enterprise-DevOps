package com.agro.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agro.backend.entities.AgroService;

public interface AgroServiceRepository extends JpaRepository<AgroService, Long> {
}
