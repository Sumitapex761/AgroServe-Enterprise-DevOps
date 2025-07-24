package com.agro.backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class AgroNotification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String message;
	
	@Column(nullable=false)
    private LocalDateTime sentAt;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private AgroBooking booking;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private AgroUser farmer; // Farmer being notified
}

