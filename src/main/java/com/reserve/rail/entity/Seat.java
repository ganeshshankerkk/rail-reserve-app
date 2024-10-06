package com.reserve.rail.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {

	@JsonProperty("seat-number")
	private int seatNumber;
	@JsonProperty("seat-type")
	private String seatType;

	public Seat(int seatNumber, String seatType) {
		this.seatNumber = seatNumber;
		this.seatType = seatType;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	@Override
	public String toString() {
		return "Seat [seatNumber=" + seatNumber + ", seatType=" + seatType + "]";
	}

	

}
