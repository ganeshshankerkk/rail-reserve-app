package com.reserve.rail.service.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reserve.rail.constants.RailReserveConstants;
import com.reserve.rail.entity.Passenger;
import com.reserve.rail.entity.Seat;
import com.reserve.rail.entity.Section;
import com.reserve.rail.entity.Ticket;
import com.reserve.rail.entity.TicketRequestBody;
import com.reserve.rail.entity.Train;

public class ServiceUtil {

	private ServiceUtil() {
	}

	static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtil.class);
	static Random random = new Random();

	/**
	 * 
	 * @param ticket The details of the ticket to be booked, including passenger
	 *               information and journey details.
	 * @param trains List of all trains available in the system
	 * @return List of trains that runs through the source and destination stations
	 *         requested in the ticket
	 */
	public static List<Train> getAllTrainsInTheRoute(TicketRequestBody ticket, List<Train> trains) {
		return trains.stream().filter(
				train -> train.getFrom().equalsIgnoreCase(ticket.getFrom()) && train.getTo().equals(ticket.getTo()))
				.toList();
	}

	/**
	 * 
	 * @param ticket The details of the ticket to be booked, including passenger
	 *               information and journey details.
	 * @param train  The train in which the seat to be booked
	 * @return true if the passenger is new and false otherwise
	 */
	public static boolean isNewPassenger(TicketRequestBody ticket, Train train) {
		return train.getSections().stream().noneMatch(section -> section.getPassengers().stream()
				.anyMatch(passenger -> passenger.getEmail().equalsIgnoreCase(ticket.getPassenger().getEmail())));
	}

	/**
	 * 
	 * @param trains  List of all trains available in the system
	 * @param trainId The id of the train to be found from the list of train
	 * @return Train object if a train with the train id exists
	 */
	public static Optional<Train> getTrain(List<Train> trains, int trainId) {
		return trains.stream().filter(trainDetails -> trainDetails.getId() == trainId).findFirst();
	}

	/**
	 * 
	 * @param trains List of trains
	 * @return train that has seats available and null otherwise
	 */
	public static Train getTrainWithVacantSeats(List<Train> trains) {
		for (Train train : trains) {
			boolean isSeatAvailableInSection = train.getSections().stream().anyMatch(Section::isSeatAvailable);
			if (isSeatAvailableInSection) {
				return train;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param train             the train in which the seat to be booked
	 * @param ticketRequestBody The details of the ticket to be booked, including
	 *                          passenger information and journey details.
	 * @param seatTypeMap       Map that holds the seat to seat type structure
	 *                          information
	 * @return passenger with the seat information updated
	 */
	public static Passenger generatePassengerTicket(Train train, TicketRequestBody ticketRequestBody,
			Map<Integer, String> seatTypeMap) {
		Passenger passenger = null;

		for (Section section : train.getSections()) {
			if (section.isSeatAvailable()) {
				List<Integer> bookedSeatNumbers = getBookedSeatNumbers(section.getPassengers());
				passenger = ticketRequestBody.getPassenger();
				Ticket ticket = generateTicket(ticketRequestBody, train);
				ticket.setSectionName(section.getName());
				ticket.setSeat(generateSeat(bookedSeatNumbers, seatTypeMap));
				passenger.setTicket(ticket);
				section.getPassengers().add(passenger);
				break;
			}
		}
		return passenger;
	}

	/**
	 * 
	 * @param passengers List of passengers traveling in the section
	 * @return list of seat numbers already occupied by the passengers
	 */
	private static List<Integer> getBookedSeatNumbers(List<Passenger> passengers) {
		return passengers.stream().map(passenger -> passenger.getTicket().getSeat().getSeatNumber()).toList();
	}

	/**
	 * 
	 * @param bookedSeats list of seat numbers already occupied by the passengers
	 * @param seatTypeMap Map that holds the seat to seat type structure information
	 * @return Seat object with the seat information updated
	 */
	private static Seat generateSeat(List<Integer> bookedSeats, Map<Integer, String> seatTypeMap) {

		boolean isSeatNumberInvalid = true;
		int seatNumber = 0;

		// finds a unique seat that is vacant and available to be booked
		while (isSeatNumberInvalid) {
			seatNumber = random.nextInt(RailReserveConstants.MAXIMUM_SEATING_CAPACITY) + 1;
			isSeatNumberInvalid = bookedSeats.contains(seatNumber);
		}

		return new Seat(seatNumber, seatTypeMap.get(seatNumber));
	}

	/**
	 * 
	 * @param ticketRequest The details of the ticket to be booked, including
	 *                      passenger information and journey details.
	 * @param train         the train in which the seat to be booked
	 * @return Ticket the generated ticket for the passenger
	 */
	private static Ticket generateTicket(TicketRequestBody ticketRequest, Train train) {

		Ticket ticket = new Ticket();

		ticket.setFrom(ticketRequest.getFrom());
		ticket.setTo(ticketRequest.getTo());
		ticket.setCost(ticketRequest.getPricePaid());
		ticket.setTrainName(train.getName());
		ticket.setId(UUID.randomUUID().toString());
		ticket.setTrainId(train.getId());

		return ticket;
	}

	/**
	 * 
	 * @param train  the train in which the seat to be booked
	 * @param ticket The details of the ticket to be booked, including passenger
	 *               information and journey details.
	 * @return true if the ticket amount paid matches with the actual train fare and
	 *         false otherwise
	 */
	public static boolean isTicketFarePaid(Train train, TicketRequestBody ticket) {
		return train.getCost().equals(ticket.getPricePaid());
	}

	/**
	 * 
	 * @param sections    List of sections with passenger details of the train
	 * @param preference  the preference given by the passenger (LB/MB/UB/SLB/SUB)
	 * @param seatTypeMap Map that holds the seat to seat type structure information
	 * @param ticket      The ticket generated to the passenger when seat was booked
	 *                    initially
	 * @return true if there are vacant seats for the given preference available and
	 *         a corresponding seat is booked and false otherwise
	 */
	public static boolean updateSeatIfAvailable(List<Section> sections, String preference,
			Map<Integer, String> seatTypeMap, Ticket ticket) {

		boolean isSeatUpdated = Boolean.FALSE;

		for (Section section : sections) {
			List<Integer> bookedSeatNumbers = getBookedSeatNumbers(section.getPassengers());
			Seat seat = generateUpdatedSeatPreference(bookedSeatNumbers, seatTypeMap, preference);
			if (null != seat) {
				ticket.setSeat(seat);
				isSeatUpdated = Boolean.TRUE;
				break;
			}
		}

		return isSeatUpdated;
	}

	/**
	 * 
	 * @param bookedSeatNumbers list of seat numbers already occupied by the
	 *                          passengers
	 * @param seatTypeMap       Map that holds the seat to seat type structure
	 *                          information
	 * @param preference        the preference given by the passenger
	 *                          (LB/MB/UB/SLB/SUB)
	 * @return Seat new seat if there are vacant seats for the given preference
	 *         available and a corresponding seat is booked and null otherwise
	 */
	private static Seat generateUpdatedSeatPreference(List<Integer> bookedSeatNumbers, Map<Integer, String> seatTypeMap,
			String preference) {

		// Fetch all seat numbers that matches the given preference
		List<Integer> preferenceSeatNumbers = seatTypeMap.entrySet().stream()
				.filter(seatType -> seatType.getValue().equalsIgnoreCase(preference)).map(Entry::getKey).toList();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("ServiceUtil: generateUpdatedSeatPreference method: preferenceSeatNumbers: {}",
					preferenceSeatNumbers);
		}
		// Filter preference seat with the available seats
		List<Integer> availableSeats = preferenceSeatNumbers.stream()
				.filter(seatNumber -> !bookedSeatNumbers.contains(seatNumber)).toList();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("ServiceUtil: generateUpdatedSeatPreference method: availableSeats: {}", availableSeats);
		}

		if (!availableSeats.isEmpty()) {
			// get a random index from the list of all available seats (0 to available seats
			// size - 1)
			int randomIndex = availableSeats.get(random.nextInt(availableSeats.size()));
			return new Seat(randomIndex, seatTypeMap.get(randomIndex));
		}

		return null;
	}

	/**
	 * 
	 * @param train the train in which the seat to be booked
	 * @return true if a seat is available in any of the sections in the train and
	 *         false otherwise
	 */
	public static boolean isSeatAvailable(Train train) {
		return train.getSections().stream().anyMatch(Section::isSeatAvailable);
	}

}
