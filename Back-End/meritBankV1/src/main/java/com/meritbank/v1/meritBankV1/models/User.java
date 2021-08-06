package com.meritbank.v1.meritBankV1.models;

import java.util.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private boolean isActive;
	
	/*
	 * @ElementCollection private List<String> roles = new ArrayList<String>();
	 */
	
	private String roles;
	
	public User() {
		
	}
		
	public User(String username, String password, boolean isActive, String role) {
		super();
		this.username = username;
		this.password = password;	
		this.isActive = isActive;
		this.roles = role;
	}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getUsername() {return username;}
	public void setUsername(String username) {	this.username = username;	}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;	}
	public boolean isActive() {	return isActive;}
	public void setActive(boolean isActive) {this.isActive = isActive;}
	public String getRoles() {return roles;	}
	
	public void setRoles(String roles) {this.roles = roles;	}

	public void appendRole(String role) {
		if(roles.isBlank())
			this.roles.concat(role);
		else
			this.roles.concat(":"+role);

	}
	
}
