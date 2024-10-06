package com.reserve.rail.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reserve.rail.constants.RailReserveConstants;

public class Section {

	private char name;
	private int capacity = RailReserveConstants.MAXIMUM_SEATING_CAPACITY;
	private List<Passenger> passengers;

	public Section(char name, List<Passenger> passengers) {
		this.name = name;
		this.passengers = passengers;
	}

	public char getName() {
		return name;
	}

	@JsonProperty("available-seats")
	public int getAvailableSeats() {
		return capacity - passengers.size();
	}

	public void setName(char name) {
		this.name = name;
	}
	
	@JsonIgnore
	public boolean isSeatAvailable() {
		return passengers.size() < capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	@Override
	public String toString() {
		return "Section [name=" + name + ", capacity=" + capacity + ", passengers=" + passengers + "]";
	}

}
