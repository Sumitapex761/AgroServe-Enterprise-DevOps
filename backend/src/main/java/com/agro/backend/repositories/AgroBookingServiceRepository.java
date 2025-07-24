package com.agro.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.backend.entities.AgroBooking;

public interface AgroBookingServiceRepository extends JpaRepository<AgroBooking, Long> {

}
