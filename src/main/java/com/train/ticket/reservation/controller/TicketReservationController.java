package com.train.ticket.reservation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.ticket.reservation.domain.booking.BookingDetails;
import com.train.ticket.reservation.domain.booking.Receipt;
import com.train.ticket.reservation.domain.train.Seat;
import com.train.ticket.reservation.domain.train.Train;
import com.train.ticket.reservation.domain.user.User;
import com.train.ticket.reservation.service.BookingService;
import com.train.ticket.reservation.service.BookingServiceImpl;

@RestController
@RequestMapping("/ticketReservation")
public class TicketReservationController {

	Logger logger = LoggerFactory.getLogger(TicketReservationController.class);

	@Autowired
	BookingService bookingService;

	@PostMapping("/purchaseTicket")
	public ResponseEntity<Receipt> purchaseTicket(@RequestBody BookingDetails ticket) {

		Receipt r = bookingService.purchaseTicket(ticket);

		if (r != null) {
			return ResponseEntity.ok(r);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(r);
		}
	}

	@GetMapping("/userReceipt")
	public ResponseEntity<List<Receipt>> userReceipt(@RequestBody User user) {
		List<Receipt> receipts = bookingService.userReceipts(user);

		if (receipts.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(receipts);
		}
		return ResponseEntity.ok(receipts);
	}

	@GetMapping("/seatAllocationBySection")
	public ResponseEntity<List<Seat>> seatAllocationBySection(@RequestBody Train train, @RequestParam String section) {
		List<Seat> seats = bookingService.usersByTrainSection(train, section);

		if (seats.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(seats);
		}
		return ResponseEntity.ok(seats);
	}

	@DeleteMapping("/removeUserFromTrain")
	public ResponseEntity<String> removeUserFromTrain(@RequestParam String from, @RequestParam String to,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber,
			@RequestParam String price) {
		Train train = new Train(from, to, price);
		User user = new User(firstName, lastName, phoneNumber);

		boolean removed = bookingService.removeUserFromTrain(train, user);

		if (removed) {
			return ResponseEntity.ok("User removed from train");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found in train");
	}

	@PutMapping("/modifyUserSeat")
	public ResponseEntity<Receipt> modifyUserSeat(@RequestParam String from, @RequestParam String to,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber,
			@RequestParam String seat, @RequestParam String cost) {

		Train train = new Train(from, to, cost);
		User user = new User(firstName, lastName, phoneNumber);

		Receipt r = bookingService.modifyUserSeat(train, user, seat);

		if (r != null) {
			return ResponseEntity.ok(r);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception ex) {
		return new ResponseEntity<>("An unexpected error occurred: ", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
