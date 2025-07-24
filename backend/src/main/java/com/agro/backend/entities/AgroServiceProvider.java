package com.agro.backend.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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

	
	    @Id @GeneratedValue
	    private Long id;

	    private String name;
	    private String companyName;
	    private String contactInfo;

	    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
	    private List<AgroService> services;
	
}
