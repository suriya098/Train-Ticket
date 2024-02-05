package com.example.trainTicket.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Ticket {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "\"from\"")
	    private String from;
		
	    @Column(name = "\"to\"")
	    private String to;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_id", referencedColumnName = "id")
	    private User user;
	    private double price;
	    
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
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public int getSeatNum() {
			return seatNum;
		}
		public void setSeatNum(int seatNum) {
			this.seatNum = seatNum;
		}
		public Ticket() {
	        // Default constructor
		}
		public Ticket(String from, String to, User user, double price) {
			super();
			this.from = from;
			this.to = to;
			this.user = user;
			this.price = price;
		}	    

}
