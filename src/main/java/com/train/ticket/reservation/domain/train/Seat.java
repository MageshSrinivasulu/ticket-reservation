package com.train.ticket.reservation.domain.train;

import java.util.Objects;

import com.train.ticket.reservation.domain.user.User;

public class Seat {

	private String seat;
	private User user;

	public Seat(String seat) {
		super();
		this.seat = seat;
	}

	public Seat(String seat, User user) {
		super();
		this.seat = seat;
		this.user = user;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seat, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return Objects.equals(seat, other.seat) && Objects.equals(user, other.user);
	}

}
