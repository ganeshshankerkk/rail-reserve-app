package com.reserve.rail.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.reserve.rail.constants.RailReserveConstants;
import com.reserve.rail.entity.Section;
import com.reserve.rail.entity.Train;
import com.reserve.rail.exception.TrainNotFoundException;
import com.reserve.rail.response.ResponseMessage;
import com.reserve.rail.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService {

	private final List<Train> trains;
	private final Map<Integer, String> seatTypeMap;

	TrainServiceImpl(List<Train> trains, Map<Integer, String> seatTypeMap) {
		this.trains = trains;
		this.seatTypeMap = seatTypeMap;
	}

	@Override
	public ResponseMessage getPassengersByTrainAndSection(int id, char sectionName) throws TrainNotFoundException {

		ResponseMessage message = new ResponseMessage();
		Optional<Train> trainOptional = trains.stream().filter(train -> train.getId() == id).findFirst();

		if (trainOptional.isEmpty()) {
			throw new TrainNotFoundException(RailReserveConstants.NO_TRAIN_AVAILABLE);
		}

		Train train = trainOptional.get();

		Optional<Section> section = train.getSections().stream().filter(sec -> sec.getName() == sectionName)
				.findFirst();

		if (section.isPresent()) {
			message.setData(new Train(train, List.of(section.get())));
		}

		message.setSuccessMessage(RailReserveConstants.REQUEST_SUCCEEDED);

		return message;
	}

	@Override
	public ResponseMessage getListOfTrains() throws TrainNotFoundException {
		ResponseMessage message = new ResponseMessage();

		if (null == trains) {
			throw new TrainNotFoundException(RailReserveConstants.NO_TRAIN_AVAILABLE);
		}

		message.setData(trains);
		message.setSuccessMessage(RailReserveConstants.REQUEST_SUCCEEDED);

		return message;
	}

	@Override
	public ResponseMessage getSeatPlan() {
		ResponseMessage message = new ResponseMessage();

		if (null == seatTypeMap) {
			message.setErrorMessage(RailReserveConstants.UNABLE_TO_FETCH_TRAIN_SEAT_PLAN);
			return message;
		}
		message.setData(seatTypeMap);
		message.setSuccessMessage(RailReserveConstants.REQUEST_SUCCEEDED);

		return message;
	}

}
