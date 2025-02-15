package com.train.ticket.reservation.domain.train;

import java.util.ArrayList;
import java.util.List;

public class Section {
	private List<Seat> availableSeats = new ArrayList<>();
	private List<Seat> unAvailableSeats = new ArrayList<>();

	
	
	private String section;

	public Section(String section) {
		super();
		this.section = section;

		availableSeats.add(new Seat(this.section + "-S1"));
		availableSeats.add(new Seat(this.section + "-S2"));
		availableSeats.add(new Seat(this.section + "-S3"));
		availableSeats.add(new Seat(this.section + "-S4"));
		availableSeats.add(new Seat(this.section + "-S5"));

	}

	public List<Seat> getAvailableSeats() {
		return availableSeats;
	}

	public List<Seat> getUnAvailableSeats() {
		return unAvailableSeats;
	}

}
