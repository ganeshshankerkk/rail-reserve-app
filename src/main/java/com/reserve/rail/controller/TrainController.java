package com.reserve.rail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reserve.rail.exception.TrainNotFoundException;
import com.reserve.rail.response.ResponseMessage;
import com.reserve.rail.service.TrainService;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

	static final Logger LOGGER = LoggerFactory.getLogger(TrainController.class);

	private final TrainService trainService;

	public TrainController(TrainService trainService) {
		this.trainService = trainService;
	}

	/**
	 * Retrieves a list of all available trains with it's corresponding sections and
	 * passengers.
	 *
	 * @return A ResponseEntity containing a ResponseMessage with the list of
	 *         trains.
	 * @throws TrainNotFoundException if no trains are found in the system.
	 */
	@GetMapping("")
	public ResponseEntity<ResponseMessage> getListOfTrains() throws TrainNotFoundException {

		LOGGER.info("Entry into TrainController :: getListOfTrains method ::");

		ResponseMessage response = trainService.getListOfTrains();

		LOGGER.info("Exit from TrainController :: getListOfTrains method :: message={}", response);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Retrieves a list of passengers for a specific train and section.
	 *
	 * @param id      The ID of the train for which the passenger list is requested.
	 * @param section The section of the train for which the passengers are to be
	 *                filtered.
	 * @return A ResponseEntity containing a ResponseMessage with the list of
	 *         passengers in the specified section.
	 * @throws TrainNotFoundException if the train associated with the provided ID
	 *                                cannot be found.
	 */
	@GetMapping("/filter")
	public ResponseEntity<ResponseMessage> getPassengersByTrainAndSection(@RequestParam int id,
			@RequestParam char section) throws TrainNotFoundException {

		LOGGER.info("Entry into TrainController :: getPassengersByTrainAndSection method :: id={}, section={}", id,
				section);

		ResponseMessage response = trainService.getPassengersByTrainAndSection(id, section);

		LOGGER.info("Exit from TrainController :: getPassengersByTrainAndSection method :: response :: {}", response);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Retrieves the standard seating plan structure for all trains.
	 *
	 * @return A ResponseEntity containing a ResponseMessage with the seat plan
	 *         details.
	 * @throws TrainNotFoundException if no seating plan information is available
	 * 
	 */
	@GetMapping("/seat-plan")
	public ResponseEntity<ResponseMessage> getSeatPlan() throws TrainNotFoundException {

		LOGGER.info("Entry into TrainController :: getSeatPlan method");

		ResponseMessage response = trainService.getSeatPlan();

		LOGGER.info("Exit from TrainController :: getSeatPlan method : response= {}", response);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
