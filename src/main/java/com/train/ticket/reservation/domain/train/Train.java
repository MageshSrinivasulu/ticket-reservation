package com.train.ticket.reservation.domain.train;

import java.util.Objects;

public class Train {
	private String from;
	private String to;
	private String price;
	private TrainSection trainSection;

	public Train(String from, String to, String price) {
		super();
		this.from = from;
		this.to = to;
		this.price = price;
		this.trainSection = new TrainSection();
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public TrainSection getTrainSection() {
		return trainSection;
	}

	public void setTrainSection(TrainSection trainSection) {
		this.trainSection = trainSection;
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, price, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Train other = (Train) obj;
		return Objects.equals(from, other.from) && Objects.equals(price, other.price) && Objects.equals(to, other.to);
	}

}
