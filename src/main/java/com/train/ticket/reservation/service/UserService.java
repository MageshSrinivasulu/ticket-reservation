package com.train.ticket.reservation.service;

import java.util.List;

import com.train.ticket.reservation.domain.booking.Receipt;
import com.train.ticket.reservation.domain.user.User;

public interface UserService {
	public void addReceipt(User user, Receipt receipt);

	public List<Receipt> getUserReceipts(User user);

	public void removeReceipt(User user, Receipt receipt);

	public void removeAllUserReceipts(User user);
}
