package com.train.ticket.reservation.service;

import java.util.List;

import com.train.ticket.reservation.domain.booking.BookingDetails;
import com.train.ticket.reservation.domain.booking.Receipt;
import com.train.ticket.reservation.domain.train.Seat;
import com.train.ticket.reservation.domain.train.Train;
import com.train.ticket.reservation.domain.user.User;

public interface BookingService {
	public Receipt purchaseTicket(BookingDetails bd);

	public List<Receipt> userReceipts(User user);

	public boolean removeUserFromTrain(Train train, User user);

	public List<Seat> usersByTrainSection(Train train, String section);

	public Receipt modifyUserSeat(Train train, User user, String seat);
}
