package com.agro.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.backend.entities.AgroNotification;
import com.agro.backend.entities.RecipientType;

public interface AgroNotificationRepository extends JpaRepository<AgroNotification, Long> {
	Optional<AgroNotification> findByBookingId(Long bookingId);
	 List<AgroNotification> findByRecipientIdAndRecipientType(Long recipientId, RecipientType recipientType);
}
