package com.agro.backend.entities;

import java.util.List;

import jakarta.persistence.Column;

//import com.sun.tools.javac.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "AgroService")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AgroService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(length = 50,unique = true)
	private String name;
	@Column(length = 100,unique = true)
    private String description;
	
	@Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private AgroServiceProvider provider;

    @OneToMany(mappedBy = "service")
    private List<AgroBooking> bookings;
}
