package com.reserve.rail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.reserve.rail.constants.RailReserveConstants;
import com.reserve.rail.entity.Passenger;
import com.reserve.rail.entity.Section;
import com.reserve.rail.entity.TicketRequestBody;
import com.reserve.rail.entity.Train;
import com.reserve.rail.entity.UpdateSeatRequest;
import com.reserve.rail.exception.PassengerNotFoundException;
import com.reserve.rail.exception.TrainNotFoundException;
import com.reserve.rail.response.ResponseMessage;
import com.reserve.rail.service.TicketService;
import com.reserve.rail.service.util.ServiceUtil;

@Service
public class TicketServiceImpl implements TicketService {

	private final List<Train> trains;
	private final Map<Integer, String> seatTypeMap;

	TicketServiceImpl(List<Train> trains, Map<Integer, String> seatTypeMap) {
		this.trains = trains;
		this.seatTypeMap = seatTypeMap;
	}

	@Override
	public ResponseMessage generateTicket(TicketRequestBody ticket) throws TrainNotFoundException {
		ResponseMessage message = new ResponseMessage();
		// Retrieve all trains available in the route
		List<Train> trainsAvailableInTheRoute = ServiceUtil.getAllTrainsInTheRoute(ticket, trains);
		if (trainsAvailableInTheRoute.isEmpty()) {
			throw new TrainNotFoundException(RailReserveConstants.NO_TRAIN_AVAILABLE);
		}
		// get train with vacant seats available
		Train train = ServiceUtil.getTrainWithVacantSeats(trainsAvailableInTheRoute);

		if (train == null) {
			message.setErrorMessage(RailReserveConstants.NO_VACANT_SEAT_AVAILABLE);
			return message;
		}

		// validates if the passenger has already booked a ticket
		if (!ServiceUtil.isNewPassenger(ticket, train)) {
			message.setErrorMessage(RailReserveConstants.PASSENGER_EXISTS_WITH_GIVEN_EMAIL_ID);
			return message;
		}

		// validates if the price paid is equal to the train ticket amount
		if (!ServiceUtil.isTicketFarePaid(train, ticket)) {
			message.setErrorMessage(RailReserveConstants.PRICE_MISMATCH + train.getCost());
			return message;
		}

		Passenger passenger = ServiceUtil.generatePassengerTicket(train, ticket, seatTypeMap);
		message.setData(passenger);
		message.setSuccessMessage(RailReserveConstants.REQUEST_SUCCEEDED);
		
		return message;
	}

	@Override
	public ResponseMessage cancelTicket(int trainId, String email) throws TrainNotFoundException {
		ResponseMessage message = new ResponseMessage();
		Map<String, Integer> cancelledTickets = new HashMap<>();

		Optional<Train> trainOptional = ServiceUtil.getTrain(trains, trainId);
		if (trainOptional.isEmpty()) {
			throw new TrainNotFoundException(RailReserveConstants.NO_TRAIN_AVAILABLE);
		}

		Train train = trainOptional.get();
		int deletedObjects = 0;

		// For every passenger record deleted, incrementing the counter by 1
		for (Section section : train.getSections()) {
			deletedObjects += section.getPassengers()
					.removeIf(passenger -> passenger.getEmail().equalsIgnoreCase(email)) ? 1 : 0;
		}

		cancelledTickets.put(RailReserveConstants.DELETED_OBJECTS, deletedObjects);
		message.setData(cancelledTickets);
		message.setSuccessMessage(RailReserveConstants.REQUEST_SUCCEEDED);

		return message;
	}

	@Override
	public ResponseMessage getTicketByTrainIdAndPassengerEmail(int trainId, String email)
			throws TrainNotFoundException, PassengerNotFoundException {
		ResponseMessage message = new ResponseMessage();
		Optional<Train> trainData = ServiceUtil.getTrain(trains, trainId);

		if (trainData.isEmpty()) {
			throw new TrainNotFoundException(RailReserveConstants.NO_TRAIN_AVAILABLE);
		}

		Optional<Passenger> passenger = trainData.stream()
				.flatMap(train -> train.getSections().stream().flatMap(section -> section.getPassengers().stream()))
				.filter(passengerObj -> passengerObj.getEmail().equalsIgnoreCase(email)).findFirst();

		if (passenger.isEmpty()) {
			throw new PassengerNotFoundException(RailReserveConstants.NO_PASSENGER_FOUND);

		}

		message.setData(passenger.get());
		message.setSuccessMessage(RailReserveConstants.REQUEST_SUCCEEDED);

		return message;

	}

	@Override
	public ResponseMessage updateTicketByPassengerPreferenceAndAvailability(UpdateSeatRequest updateSeatRequest)
			throws PassengerNotFoundException, TrainNotFoundException {

		ResponseMessage message = new ResponseMessage();
		Optional<Train> trainData = ServiceUtil.getTrain(trains, updateSeatRequest.getTrainId());

		if (trainData.isEmpty()) {
			throw new TrainNotFoundException(RailReserveConstants.NO_TRAIN_AVAILABLE);
		}
		if (!ServiceUtil.isSeatAvailable(trainData.get())) {
			message.setErrorMessage(RailReserveConstants.NO_VACANT_SEAT_AVAILABLE);
			return message;
		}

		Optional<Passenger> passenger = trainData.stream()
				.flatMap(train -> train.getSections().stream().flatMap(section -> section.getPassengers().stream()))
				.filter(passengerObj -> passengerObj.getEmail().equalsIgnoreCase(updateSeatRequest.getEmail()))
				.findFirst();

		if (passenger.isEmpty())
			throw new PassengerNotFoundException(RailReserveConstants.NO_PASSENGER_FOUND);

		if (!passenger.get().getTicket().getId().equals(updateSeatRequest.getTicketId())) {
			message.setErrorMessage(RailReserveConstants.PASSENGER_TICKET_MISMATCH);
			return message;
		}

		boolean isSeatUpdated = ServiceUtil.updateSeatIfAvailable(trainData.get().getSections(),
				updateSeatRequest.getPreference(), seatTypeMap, passenger.get().getTicket());

		if (!isSeatUpdated) {
			message.setErrorMessage(RailReserveConstants.NO_VACANT_SEAT_AVAILABLE_FOR_PREFERENCE);
		} else {
			message.setData(passenger);
			message.setSuccessMessage(RailReserveConstants.REQUEST_SUCCEEDED);
		}
		return message;
	}

}
