package com.reserve.rail.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketRequestBody {

	private String from;
	private String to;
	@JsonProperty("price-paid")
	private BigDecimal pricePaid;
	private Passenger passenger;

	public TicketRequestBody(String from, String to, BigDecimal pricePaid, Passenger passenger) {
		this.from = from;
		this.to = to;
		this.pricePaid = pricePaid;
		this.passenger = passenger;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public BigDecimal getPricePaid() {
		return pricePaid;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	@Override
	public String toString() {
		return "TicketRequestBody [from=" + from + ", to=" + to + ", pricePaid=" + pricePaid + ", passenger="
				+ passenger + "]";
	}

}
