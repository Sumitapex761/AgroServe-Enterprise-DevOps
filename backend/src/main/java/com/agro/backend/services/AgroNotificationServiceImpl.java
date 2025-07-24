package com.agro.backend.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.repositories.AgroNotificationServiceRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroNotificationServiceImpl implements AgroNotificationService {
	private final ModelMapper modelmapper;
	private final AgroNotificationServiceRepository notificationRepository;
}
