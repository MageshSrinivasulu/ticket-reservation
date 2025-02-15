package com.train.ticket.reservation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.train.ticket.reservation.domain.booking.Receipt;
import com.train.ticket.reservation.domain.user.User;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	Map<User, List<Receipt>> userAllocationMap = new HashMap<>();

	@Override
	public void addReceipt(User user, Receipt receipt) {
		logger.info("Creating receipt for the user {} {}", user.getFirstName(), user.getLastName());

		if (!userAllocationMap.containsKey(user)) {
			List<Receipt> receipts = new ArrayList<>();
			receipts.add(receipt);
			userAllocationMap.put(user, receipts);
		} else {
			userAllocationMap.get(user).add(receipt);
		}
	}

	@Override
	public List<Receipt> getUserReceipts(User user) {
		logger.info("Fetching receipts for the user {} {}", user.getFirstName(), user.getLastName());

		List<Receipt> receipts = new ArrayList<Receipt>();

		if (userAllocationMap.containsKey(user)) {
			receipts.addAll(userAllocationMap.get(user));
		}

		return receipts;
	}

	@Override
	public void removeReceipt(User user, Receipt receipt) {
		logger.info("Removing receipts for the user {} {}", user.getFirstName(), user.getLastName());
		if (userAllocationMap.containsKey(user)) {
			userAllocationMap.get(user).remove(receipt);
		}
	}

	@Override
	public void removeAllUserReceipts(User user) {
		if (userAllocationMap.containsKey(user)) {
			userAllocationMap.get(user).clear();
		}
	}
}
