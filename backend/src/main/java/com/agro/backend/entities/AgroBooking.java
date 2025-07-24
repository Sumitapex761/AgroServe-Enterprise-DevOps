package com.agro.backend.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.ToString;

@Entity
@Table(name = "AgroBooking")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AgroBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@CreationTimestamp
	@Column(name = "booked_on")
    private LocalDateTime bookingTime;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private Status status; // PENDING, CONFIRMED, COMPLETED

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AgroUser user; // Customer who made the booking

    @ManyToOne
    @JoinColumn(name = "service_id")
    private AgroService service;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private AgroNotification notification;
}
