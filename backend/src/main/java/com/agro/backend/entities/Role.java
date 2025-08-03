package com.agro.backend.entities;

public enum Role {
 SERVICEPROVIDER, FARMER, ADMIN;

public String getAuthority() {
	
	return "ROLE"+this.name();
}
}
