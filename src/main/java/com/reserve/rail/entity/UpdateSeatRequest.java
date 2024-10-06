package com.reserve.rail.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateSeatRequest {

	@JsonProperty("ticket-id")
	private String ticketId;

	@JsonProperty("train-id")
	private int trainId;

	private String email;

	private String preference;

	public String getTicketId() {
		return ticketId;
	}

	public int getTrainId() {
		return trainId;
	}

	public String getEmail() {
		return email;
	}

	public String getPreference() {
		return preference;
	}

	@Override
	public String toString() {
		return "UpdateSeatRequest [ticketId=" + ticketId + ", trainId=" + trainId + ", email=" + email + ", preference="
				+ preference + "]";
	}

}
