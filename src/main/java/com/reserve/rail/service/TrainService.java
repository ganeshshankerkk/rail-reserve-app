package com.reserve.rail.service;

import com.reserve.rail.exception.TrainNotFoundException;
import com.reserve.rail.response.ResponseMessage;

public interface TrainService {

	/**
	 * Retrieves a list of all available trains with it's corresponding sections and
	 * passengers.
	 *
	 * @param id      The ID of the train for which the passenger list is requested.
	 * @param section The section of the train for which the passengers are to be
	 *                filtered.
	 * @return A ResponseMessage with the list of passengers in the specified
	 *         section.
	 * @throws TrainNotFoundException if the train associated with the provided ID
	 *                                cannot be found.
	 */
	ResponseMessage getPassengersByTrainAndSection(int id, char section) throws TrainNotFoundException;

	/**
	 * Retrieves a list of all available trains.
	 *
	 * @return A ResponseMessage with the list of trains.
	 * @throws TrainNotFoundException if no trains are found in the system.
	 */
	ResponseMessage getListOfTrains() throws TrainNotFoundException;

	/**
	 * Retrieves the standard seating plan structure for all trains.
	 *
	 * @return A ResponseMessage with the seat plan details.
	 * @throws TrainNotFoundException if no seating plan information is available
	 * 
	 */
	ResponseMessage getSeatPlan();

}
