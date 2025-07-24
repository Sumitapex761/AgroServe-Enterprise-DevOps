package com.agro.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.backend.entities.AgroNotification;

public interface AgroNotificationServiceRepository extends JpaRepository<AgroNotification, Long> {

}
