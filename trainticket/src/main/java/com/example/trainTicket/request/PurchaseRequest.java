package com.example.trainTicket.request;

import com.example.trainTicket.entity.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PurchaseRequest {
	
	@NotBlank(message = "Departure is mandatory")
	private String from;
	
	@NotBlank(message = "Destination is mandatory")
    private String to;
	
	@Valid
    @NotNull(message = "User is mandatory")
    private User user;
	
	@NotNull(message = "Price is mandatory")
    private double price;
	
    private String allocatedSection;
    
    private int seatNum;
    
    
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAllocatedSection() {
		return allocatedSection;
	}
	public void setAllocatedSection(String allocatedSection) {
		this.allocatedSection = allocatedSection;
	}
	
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public PurchaseRequest(String from, String to, User user, double price, String allocatedSection) {
		super();
		this.from = from;
		this.to = to;
		this.user = user;
		this.price = price;
		this.allocatedSection = allocatedSection;
	}
	    
}
