package com.train.ticket.reservation.service;

import com.train.ticket.reservation.domain.train.Seat;
import com.train.ticket.reservation.domain.train.Train;
import com.train.ticket.reservation.domain.user.User;

public interface TrainService {
	public boolean isAvailable(Train train);

	public Train getTrain(Train train);

	public Seat blockSet(Train train, User user);

	public boolean removeUserFromTrain(Train train, User user);

	public Seat modifyUserSeat(Train train, User user, String seat);
}
