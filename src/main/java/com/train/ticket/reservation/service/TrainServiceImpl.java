package com.train.ticket.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.train.ticket.reservation.domain.train.Seat;
import com.train.ticket.reservation.domain.train.Section;
import com.train.ticket.reservation.domain.train.Train;
import com.train.ticket.reservation.domain.user.User;

@Service
public class TrainServiceImpl implements TrainService {

	Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);

	private List<Train> trains = new ArrayList<>();

	{
		Train train = new Train("London", "France", "$20");
		trains.add(train);
	}

	@Override
	public boolean isAvailable(Train train) {
		Train availableTrain = trains.get(trains.indexOf(train));

		if (availableTrain != null) {
			if (!availableTrain.getTrainSection().getSectionA().getAvailableSeats().isEmpty()
					|| !availableTrain.getTrainSection().getSectionB().getAvailableSeats().isEmpty()) {

				logger.info("Seats are avaiable in train from {} to {}", train.getFrom(), train.getTo());

				return true;
			}

		}

		logger.info("Seats are not avaiable in train from {} to {}", train.getFrom(), train.getTo());
		return false;
	}

	@Override
	public Seat blockSet(Train train, User user) {
		Train availableTrain = trains.get(trains.indexOf(train));

		Seat seat = null;

		if (!availableTrain.getTrainSection().getSectionA().getAvailableSeats().isEmpty()) {
			seat = availableTrain.getTrainSection().getSectionA().getAvailableSeats().get(0);
			seat.setUser(user);

			availableTrain.getTrainSection().getSectionA().getUnAvailableSeats().add(seat);

			availableTrain.getTrainSection().getSectionA().getAvailableSeats().remove(0);

		} else if (!availableTrain.getTrainSection().getSectionB().getAvailableSeats().isEmpty()) {
			seat = availableTrain.getTrainSection().getSectionB().getAvailableSeats().get(0);
			seat.setUser(user);

			availableTrain.getTrainSection().getSectionB().getUnAvailableSeats().add(seat);
			availableTrain.getTrainSection().getSectionB().getAvailableSeats().remove(0);
		}

		if (seat != null) {
			logger.info("Blocking seat {} for user {} {} in train from {} to {}", seat.getSeat(), user.getFirstName(),
					user.getLastName(), train.getFrom(), train.getTo());
		}

		return seat;
	}

	@Override
	public Train getTrain(Train train) {
		return trains.get(trains.indexOf(train));
	}

	@Override
	public boolean removeUserFromTrain(Train train, User user) {
		logger.info("Removing user {} {} from train traveling from {} to {}", user.getFirstName(), user.getLastName(),
				train.getFrom(), train.getTo());

		Train t = trains.get(trains.indexOf(train));

		boolean removed = false;

		for (Seat s : t.getTrainSection().getSectionA().getUnAvailableSeats()) {
			if (s.getUser().equals(user)) {
				deAllocateSet(t.getTrainSection().getSectionA(), s);
				removed = true;
			}
		}

		for (Seat s : t.getTrainSection().getSectionB().getUnAvailableSeats()) {
			if (s.getUser().equals(user)) {
				deAllocateSet(t.getTrainSection().getSectionB(), s);
				removed = true;
			}
		}

		return removed;

	}

	@Override
	public Seat modifyUserSeat(Train train, User user, String seat) {
		logger.info("Modifying user {} {} from train traveling from {} to {}", user.getFirstName(), user.getLastName(),
				train.getFrom(), train.getTo());

		Train t = trains.get(trains.indexOf(train));

		boolean modified = false;
		for (Seat s : t.getTrainSection().getSectionA().getUnAvailableSeats()) {
			if (s.getSeat().equals(seat) && s.getUser().equals(user)) {
				deAllocateSet(t.getTrainSection().getSectionA(), s);
				modified = true;
				break;
			}
		}

		for (Seat s : t.getTrainSection().getSectionB().getUnAvailableSeats()) {
			if (s.getSeat().equals(seat) && s.getUser().equals(user)) {
				deAllocateSet(t.getTrainSection().getSectionB(), s);
				modified = true;
				break;
			}
		}

		Seat modifiedSeat = null;

		if (modified) {
			modifiedSeat = blockSet(train, user);
		}

		return modifiedSeat;
	}

	private void deAllocateSet(Section section, Seat seat) {
		seat.setUser(null);
		section.getUnAvailableSeats().remove(seat);
		section.getAvailableSeats().add(seat);
	}

}
