package com.agro.backend.services;

public interface EmailService {
	void sendBookingNotification(String to, String subject, String htmlContent);
}
