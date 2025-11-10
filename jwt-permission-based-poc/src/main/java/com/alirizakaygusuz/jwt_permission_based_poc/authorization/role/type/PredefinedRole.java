package com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.type;

public enum PredefinedRole {

	SUPER_ADMIN("SUPER_ADMIN", "Full system access"),
	USER("USER", "Standard user access");
	
	private final String name;
    private final String description;
    
    
    
    private PredefinedRole(String name , String description) {
    	this.name = name;
    	this.description = description;
    }
    
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
    
    
    
}
