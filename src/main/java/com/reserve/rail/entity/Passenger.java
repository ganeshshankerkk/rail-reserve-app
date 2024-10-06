package com.reserve.rail.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger {
	
	@JsonProperty("first-name")
	private String firstName;
	@JsonProperty("last-name")
	private String lastName;
	private String email;
	private Ticket ticket;
	
	
	public Passenger(String firstName, String lastName, String email, Ticket ticket) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.ticket = ticket;
	}
	
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
	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Passenger [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", ticket="
				+ ticket + "]";
	}

	
	
	

}
