package com.reserve.rail.entity;

import java.math.BigDecimal;
import java.util.List;

public class Train {

	private int id;
	private String name;
	private String from;
	private String to;
	private BigDecimal cost;
	private List<Section> sections;

	public Train(int id, String name, String from, String to, List<Section> sections, BigDecimal cost) {
		this.id = id;
		this.name = name;
		this.from = from;
		this.to = to;
		this.sections = sections;
		this.cost = cost;
	}

	public Train(Train train, List<Section> sections) {
		this.id = train.getId();
		this.name = train.getName();
		this.from = train.getFrom();
		this.to = train.getTo();
		this.sections = sections;
		this.cost = train.getCost();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", name=" + name + ", from=" + from + ", to=" + to + ", cost=" + cost + ", sections="
				+ sections + "]";
	}

}
