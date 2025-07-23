package com.agro.backend.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Status status; // PENDING, CONFIRMED, COMPLETED

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user; // Customer who made the booking
//
//    @ManyToOne
//    @JoinColumn(name = "service_id")
//    private Service service;
//
//    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
//    private Notification notification;
}
