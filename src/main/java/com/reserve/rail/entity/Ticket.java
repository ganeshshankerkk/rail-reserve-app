package com.reserve.rail.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

	private String id;
	private String from;
	private String to;
	private BigDecimal cost;
	@JsonProperty("train-id")
	private int trainId;
	@JsonProperty("train-name")
	private String trainName;
	@JsonProperty("section-name")
	private char sectionName;
	private Seat seat;

	public Ticket() {
		super();
	}

	public Ticket(String id, int trainId, String from, String to, BigDecimal cost, String trainName, char sectionName,
			Seat seat) {
		this.id = id;
		this.trainId = trainId;
		this.from = from;
		this.to = to;
		this.cost = cost;
		this.trainName = trainName;
		this.sectionName = sectionName;
		this.seat = seat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public char getSectionName() {
		return sectionName;
	}

	public void setSectionName(char sectionName) {
		this.sectionName = sectionName;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", from=" + from + ", to=" + to + ", cost=" + cost + ", trainId=" + trainId
				+ ", trainName=" + trainName + ", sectionName=" + sectionName + ", seat=" + seat + "]";
	}

}
