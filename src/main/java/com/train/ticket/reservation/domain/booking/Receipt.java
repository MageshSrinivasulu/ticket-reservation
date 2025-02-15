package com.train.ticket.reservation.domain.booking;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Receipt {
	private String from;
	private String to;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String price;
	private String seat;
	private Timestamp timestamp = Timestamp.from(Instant.now());

	public Receipt(String from, String to, String firstName, String lastName, String phoneNumber, String price,
			String seat) {
		super();
		this.from = from;
		this.to = to;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.price = price;
		this.seat = seat;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, from, lastName, phoneNumber, price, seat, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receipt other = (Receipt) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(from, other.from)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(price, other.price) && Objects.equals(seat, other.seat)
				&& Objects.equals(to, other.to);
	}

}
