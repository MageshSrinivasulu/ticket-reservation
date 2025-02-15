package com.train.ticket.reservation.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.ticket.reservation.domain.booking.BookingDetails;
import com.train.ticket.reservation.domain.booking.Receipt;
import com.train.ticket.reservation.domain.train.Seat;
import com.train.ticket.reservation.domain.train.Train;
import com.train.ticket.reservation.domain.user.User;

@Service
public class BookingServiceImpl implements BookingService {

	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	UserService userService;

	@Autowired
	TrainService trainService;

	@Override
	public Receipt purchaseTicket(BookingDetails bd) {

		logger.info("Purchasing ticket for the user {} {} for traveling from {} to {} ", bd.getFirstName(),
				bd.getLastName(), bd.getFrom(), bd.getTo());

		Train train = new Train(bd.getFrom(), bd.getTo(), bd.getPrice());
		User user = new User(bd.getFirstName(), bd.getLastName(), bd.getPhoneNumber());

		Seat availableSeat = null;
		Receipt r = null;
		if (trainService.isAvailable(train)) {

			availableSeat = trainService.blockSet(train, user);

			r = new Receipt(bd.getFrom(), bd.getTo(), bd.getFirstName(), bd.getLastName(), bd.getPhoneNumber(),
					bd.getPrice(), availableSeat.getSeat());

			userService.addReceipt(user, r);
		}

		return r;
	}

	@Override
	public List<Receipt> userReceipts(User user) {
		return userService.getUserReceipts(user);
	}

	@Override
	public boolean removeUserFromTrain(Train train, User user) {
		userService.removeAllUserReceipts(user);
		return trainService.removeUserFromTrain(train, user);

	}

	@Override
	public List<Seat> usersByTrainSection(Train train, String section) {
		Train t = trainService.getTrain(train);

		List<Seat> seats = new ArrayList<Seat>();

		if (section.equals("SectionA")) {
			seats.addAll(t.getTrainSection().getSectionA().getUnAvailableSeats());
		} else {
			seats.addAll(t.getTrainSection().getSectionB().getUnAvailableSeats());
		}

		return seats;
	}

	@Override
	public Receipt modifyUserSeat(Train train, User user, String seat) {

		Seat modifiedSeat = trainService.modifyUserSeat(train, user, seat);
		Receipt r = null;

		if (modifiedSeat != null) {
			r = new Receipt(train.getFrom(), train.getTo(), user.getFirstName(), user.getLastName(),
					user.getPhoneNumber(), train.getPrice(), seat);

			userService.removeReceipt(user, r);

			r.setSeat(modifiedSeat.getSeat());
			r.setTimestamp(Timestamp.from(Instant.now()));

			userService.addReceipt(user, r);
		}
		return r;
	}

}
