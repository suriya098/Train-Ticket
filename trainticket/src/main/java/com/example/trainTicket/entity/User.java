package com.example.trainTicket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_profile")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "First name is mandatory")
	private String firstName;
	
	@NotBlank(message = "Last name is mandatory")
    private String lastName;
	
	@Email(message = "Invalid email address")
    @NotBlank(message = "Email is mandatory")
    private String email;
	
    private String allocatedSection;
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
        return id;
    }
	
	public String getAllocatedSection() {
		return allocatedSection;
	}
	public void setAllocatedSection(String allocatedSection) {
		this.allocatedSection = allocatedSection;
	}
	
	public User() {
        // Default constructor
    }
	
	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}  
	
	
}
