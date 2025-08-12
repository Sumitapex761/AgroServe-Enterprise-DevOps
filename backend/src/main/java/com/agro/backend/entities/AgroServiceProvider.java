package com.agro.backend.entities;

import java.util.ArrayList;
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
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "services")
public class AgroServiceProvider {

	
	    @Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false, unique = true)
	    private String email;
	    
	    @Column(nullable = false)
	    private String name;
	    
	    @Column(nullable = false)
	    private String companyName;
	    
	    @Column(nullable = false)
	    private String contactInfo;

	    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<AgroService> services = new ArrayList<>();
	    
	    // helper method to add service 
	    public void addService(AgroService service) {
	        services.add(service);
	        service.setProvider(this);  // set the back-reference
	    }
	    
	    // helper method to remove a service 
	    public void removeService(AgroService service) {
	        services.remove(service);  // mathched by the IDs
	        service.setProvider(null);  // remove the back-reference
	    }
	    
	
}
