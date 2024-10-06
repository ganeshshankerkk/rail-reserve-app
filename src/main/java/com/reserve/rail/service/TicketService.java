package com.reserve.rail.service;

import com.reserve.rail.entity.TicketRequestBody;
import com.reserve.rail.entity.UpdateSeatRequest;
import com.reserve.rail.exception.PassengerNotFoundException;
import com.reserve.rail.exception.TrainNotFoundException;
import com.reserve.rail.response.ResponseMessage;

public interface TicketService {

	/**
	 * Books a ticket for a passenger based on the provided ticket request details.
	 *
	 * @param ticket The details of the ticket to be booked, including passenger
	 *               information and journey details.
	 * @return A ResponseMessage with the booking confirmation.
	 * @throws TrainNotFoundException if the train associated with the ticket
	 *                                request cannot be found.
	 */
	public ResponseMessage generateTicket(TicketRequestBody ticket) throws TrainNotFoundException;

	/**
	 * Cancels a ticket for a passenger and removes the associated passenger from
	 * the train.
	 *
	 * @param id    The ID of the train associated with the ticket to be canceled.
	 * @param email The email of the passenger whose ticket is to be canceled.
	 * @return A ResponseMessage with the cancellation confirmation.
	 * @throws TrainNotFoundException if the train associated with the ticket cannot
	 *                                be found.
	 */
	public ResponseMessage cancelTicket(int trainId, String email) throws TrainNotFoundException;

	/**
	 * Retrieves a ticket for a passenger based on the train ID and passenger email.
	 *
	 * @param id    The ID of the train for which the ticket is being retrieved.
	 * @param email The email of the passenger whose ticket is being retrieved.
	 * @return A ResponseMessage with the ticket details.
	 * @throws TrainNotFoundException     if the train associated with the ticket
	 *                                    cannot be found.
	 * @throws PassengerNotFoundException if no ticket is found for the specified
	 *                                    passenger email.
	 */
	public ResponseMessage getTicketByTrainIdAndPassengerEmail(int trainId, String email)
			throws TrainNotFoundException, PassengerNotFoundException;

	/**
	 * Updates a ticket based on passenger preference and availability.
	 *
	 * @param updateSeatRequest Request with the train id, ticket id, passenger
	 *                          email, and passenger seat preference to update the
	 *                          seat based on seat availability
	 * @return A ResponseMessage with the update confirmation and seat information.
	 * @throws TrainNotFoundException     if the train associated with the ticket
	 *                                    cannot be found.
	 * @throws PassengerNotFoundException if no ticket is found for the specified
	 *                                    passenger email.
	 */
	public ResponseMessage updateTicketByPassengerPreferenceAndAvailability(UpdateSeatRequest updateSeatRequest)
			throws TrainNotFoundException, PassengerNotFoundException;

}
