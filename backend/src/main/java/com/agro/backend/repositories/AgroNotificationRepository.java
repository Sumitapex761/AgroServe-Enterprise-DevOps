package com.agro.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.backend.entities.AgroNotification;

public interface AgroNotificationRepository extends JpaRepository<AgroNotification, Long> {
	Optional<AgroNotification> findByBookingId(Long bookingId);
}
