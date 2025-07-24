package com.agro.backend.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class AgroServiceProvider {

	
	    @Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false)
	    private String name;
	    
	    @Column(nullable = false)
	    private String companyName;
	    
	    @Column(nullable = false)
	    private String contactInfo;

	    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
	    private List<AgroService> services;
	
}
