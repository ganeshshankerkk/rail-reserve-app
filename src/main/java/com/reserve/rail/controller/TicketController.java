package com.reserve.rail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reserve.rail.constants.RailReserveConstants;
import com.reserve.rail.entity.TicketRequestBody;
import com.reserve.rail.entity.UpdateSeatRequest;
import com.reserve.rail.exception.PassengerNotFoundException;
import com.reserve.rail.exception.TrainNotFoundException;
import com.reserve.rail.response.ResponseMessage;
import com.reserve.rail.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

	static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

	private final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	/**
	 * Books a ticket for a passenger based on the provided ticket request details.
	 *
	 * @param ticket The details of the ticket to be booked, including passenger
	 *               information and journey details.
	 * @return A ResponseEntity containing a ResponseMessage with the booking
	 *         confirmation.
	 * @throws TrainNotFoundException if the train associated with the ticket
	 *                                request cannot be found.
	 */
	@PostMapping("/book")
	public ResponseEntity<ResponseMessage> bookTicket(@RequestBody TicketRequestBody ticket)
			throws TrainNotFoundException {

		LOGGER.info("Entry into TicketController :: bookTicket method :: TicketRequestBody :: {}", ticket);

		ResponseMessage message = ticketService.generateTicket(ticket);

		LOGGER.info("Exit from TicketController :: bookTicket method :: message :: {}", message);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * Cancels a ticket for a passenger and removes the associated passenger from
	 * the train.
	 *
	 * @param id    The ID of the train associated with the ticket to be canceled.
	 * @param email The email of the passenger whose ticket is to be canceled.
	 * @return A ResponseEntity containing a ResponseMessage with the cancellation
	 *         confirmation.
	 * @throws TrainNotFoundException if the train associated with the ticket cannot
	 *                                be found.
	 */
	@DeleteMapping("/cancel")
	public ResponseEntity<ResponseMessage> cancelTicket(@RequestParam(name = RailReserveConstants.TRAIN_ID) int id,
			@RequestParam String email) throws TrainNotFoundException {

		LOGGER.info("Entry into TicketController :: cancelTicket method :: id={}, email={}", id, email);

		ResponseMessage message = ticketService.cancelTicket(id, email);

		LOGGER.info("Exit from TicketController :: cancelTicket method :: message :: {}", message);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * Retrieves a ticket for a passenger based on the train ID and passenger email.
	 *
	 * @param id    The ID of the train for which the ticket is being retrieved.
	 * @param email The email of the passenger whose ticket is being retrieved.
	 * @return A ResponseEntity containing a ResponseMessage with the ticket
	 *         details.
	 * @throws TrainNotFoundException     if the train associated with the ticket
	 *                                    cannot be found.
	 * @throws PassengerNotFoundException if no ticket is found for the specified
	 *                                    passenger email.
	 */
	@GetMapping("/receipt")
	public ResponseEntity<ResponseMessage> getTicketByTrainIdAndPassengerEmail(
			@RequestParam(name = RailReserveConstants.TRAIN_ID) int id, @RequestParam String email)
			throws TrainNotFoundException, PassengerNotFoundException {
		LOGGER.info("Entry into TicketController :: getTicketByTrainIdAndPassengerEmail method :: id={}, email={}", id,
				email);

		ResponseMessage message = ticketService.getTicketByTrainIdAndPassengerEmail(id, email);

		LOGGER.info("Exit from TicketController :: getTicketByTrainIdAndPassengerEmail method :: message :: {}",
				message);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * Updates a ticket based on passenger preference and availability.
	 * 
	 * @param updateSeatRequest Request with the train id, ticket id, passenger
	 *                          email, and passenger seat preference to update the
	 *                          seat based on seat availability
	 * @return ResponseEntity containing a ResponseMessage with the update
	 *         confirmation.
	 * @throws TrainNotFoundException     if the train associated with the ticket
	 *                                    cannot be found.
	 * @throws PassengerNotFoundException if no ticket is found for the specified
	 *                                    passenger email.
	 */
	@PatchMapping("/update")
	public ResponseEntity<ResponseMessage> updateTicketByPassengerPreferenceAndAvailability(
			@RequestBody UpdateSeatRequest updateSeatRequest)
			throws TrainNotFoundException, PassengerNotFoundException {
		LOGGER.info(
				"Entry into TicketController :: updateTicketByPassengerPreferenceAndAvailability method :: updateSeatRequest={}",
				updateSeatRequest);

		ResponseMessage message = ticketService.updateTicketByPassengerPreferenceAndAvailability(updateSeatRequest);

		LOGGER.info(
				"Exit from TicketController :: updateTicketByPassengerPreferenceAndAvailability method :: message :: {}",
				message);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
