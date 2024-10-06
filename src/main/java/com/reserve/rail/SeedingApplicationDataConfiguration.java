package com.reserve.rail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reserve.rail.constants.RailReserveConstants;
import com.reserve.rail.entity.Passenger;
import com.reserve.rail.entity.Seat;
import com.reserve.rail.entity.Section;
import com.reserve.rail.entity.Ticket;
import com.reserve.rail.entity.Train;

@Configuration
public class SeedingApplicationDataConfiguration {

	@Bean
	List<Train> trains() {
		List<Train> trains = new ArrayList<>();
		trains.add(new Train(RailReserveConstants.WEST_COST_EXPRESS_ID, RailReserveConstants.WEST_COST_EXPRESS,
				RailReserveConstants.CHENNAI, RailReserveConstants.MANGALORE,
				trainToSectionMap().get(RailReserveConstants.WEST_COST_EXPRESS_ID), BigDecimal.valueOf(20)));
		trains.add(new Train(RailReserveConstants.CBE_VANDE_BHARAT_ID, RailReserveConstants.CBE_VANDE_BHARAT,
				RailReserveConstants.BANGALORE, RailReserveConstants.COIMBATORE,
				trainToSectionMap().get(RailReserveConstants.CBE_VANDE_BHARAT_ID), BigDecimal.valueOf(30)));
		trains.add(new Train(RailReserveConstants.EUROSTAR_TRAIN_9014, RailReserveConstants.EUROSTAR,
				RailReserveConstants.LONDON_ST_PANCRAS_INTERNATIONAL, RailReserveConstants.PARIS_NORD,
				trainToSectionMap().get(RailReserveConstants.EUROSTAR_TRAIN_9014), BigDecimal.valueOf(20)));
		return trains;
	}

	@Bean
	Map<Integer, List<Section>> trainToSectionMap() {
		Map<Integer, List<Section>> trainToSectionMap = new HashMap<>();
		List<Section> sections = new ArrayList<>();

		List<Passenger> passengers = new ArrayList<>();
		passengers.add(createPassenger("Ganesh", "Shanker", "ganesh.s@railreserve.com",
				RailReserveConstants.WEST_COST_EXPRESS_ID, RailReserveConstants.CHENNAI, RailReserveConstants.MANGALORE,
				20, RailReserveConstants.WEST_COST_EXPRESS, RailReserveConstants.SECTION_A, 1,
				RailReserveConstants.LB));
		passengers.add(createPassenger("Raj", "P", "raj.p@railreserve.com", RailReserveConstants.WEST_COST_EXPRESS_ID,
				RailReserveConstants.CHENNAI, RailReserveConstants.MANGALORE, 20,
				RailReserveConstants.WEST_COST_EXPRESS, RailReserveConstants.SECTION_A, 2, RailReserveConstants.MB));
		passengers.add(createPassenger("Suraj", "Sharma", "suraj.s@railreserve.com",
				RailReserveConstants.WEST_COST_EXPRESS_ID, RailReserveConstants.CHENNAI, RailReserveConstants.MANGALORE,
				20, RailReserveConstants.WEST_COST_EXPRESS, RailReserveConstants.SECTION_A, 3,
				RailReserveConstants.UB));
		sections.add(new Section(RailReserveConstants.SECTION_A, passengers));

		passengers = new ArrayList<>();
		passengers.add(createPassenger("Supriya", "P S", "supriya.ps@railreserve.com",
				RailReserveConstants.WEST_COST_EXPRESS_ID, RailReserveConstants.CHENNAI, RailReserveConstants.MANGALORE,
				20, RailReserveConstants.WEST_COST_EXPRESS, RailReserveConstants.SECTION_B, 1,
				RailReserveConstants.LB));
		passengers.add(createPassenger("Anirudh", "Dheep", "dheep.anirudh@railreserve.com",
				RailReserveConstants.WEST_COST_EXPRESS_ID, RailReserveConstants.CHENNAI, RailReserveConstants.MANGALORE,
				20, RailReserveConstants.WEST_COST_EXPRESS, RailReserveConstants.SECTION_B, 2,
				RailReserveConstants.MB));
		passengers.add(createPassenger("Sahlin", "Ahmed", "sahlin.s@railreserve.com",
				RailReserveConstants.WEST_COST_EXPRESS_ID, RailReserveConstants.CHENNAI, RailReserveConstants.MANGALORE,
				20, RailReserveConstants.WEST_COST_EXPRESS, RailReserveConstants.SECTION_B, 3,
				RailReserveConstants.UB));

		sections.add(new Section(RailReserveConstants.SECTION_B, passengers));
		trainToSectionMap.put(RailReserveConstants.WEST_COST_EXPRESS_ID, sections);

		sections = new ArrayList<>();
		passengers = new ArrayList<>();

		passengers.add(createPassenger("Arjun", "Surya", "arjun.s@railreserve.com",
				RailReserveConstants.CBE_VANDE_BHARAT_ID, RailReserveConstants.BANGALORE,
				RailReserveConstants.COIMBATORE, 30, RailReserveConstants.CBE_VANDE_BHARAT,
				RailReserveConstants.SECTION_A, 1, RailReserveConstants.LB));

		passengers.add(createPassenger("Gaurav", "Agarwal", "gaurav.a@railreserve.com",
				RailReserveConstants.CBE_VANDE_BHARAT_ID, RailReserveConstants.BANGALORE,
				RailReserveConstants.COIMBATORE, 30, RailReserveConstants.CBE_VANDE_BHARAT,
				RailReserveConstants.SECTION_A, 2, RailReserveConstants.MB));

		passengers.add(createPassenger("Ritvik", "Aryendra", "ritvik.a@railreserve.com",
				RailReserveConstants.CBE_VANDE_BHARAT_ID, RailReserveConstants.BANGALORE,
				RailReserveConstants.COIMBATORE, 30, RailReserveConstants.CBE_VANDE_BHARAT,
				RailReserveConstants.SECTION_A, 3, RailReserveConstants.UB));

		sections.add(new Section(RailReserveConstants.SECTION_A, passengers));

		passengers = new ArrayList<>();

		passengers.add(createPassenger("Ishani", "Chatterjee", "ishani.c@railreserve.com",
				RailReserveConstants.CBE_VANDE_BHARAT_ID, RailReserveConstants.BANGALORE,
				RailReserveConstants.COIMBATORE, 30, RailReserveConstants.CBE_VANDE_BHARAT,
				RailReserveConstants.SECTION_B, 1, RailReserveConstants.LB));

		passengers.add(createPassenger("Sita", "Reddy", "sita.reddy@railreserve.com",
				RailReserveConstants.CBE_VANDE_BHARAT_ID, RailReserveConstants.BANGALORE,
				RailReserveConstants.COIMBATORE, 30, RailReserveConstants.CBE_VANDE_BHARAT,
				RailReserveConstants.SECTION_B, 2, RailReserveConstants.MB));

		passengers.add(createPassenger("Deepak", "Chowdhury", "deepak.s@railreserve.com",
				RailReserveConstants.CBE_VANDE_BHARAT_ID, RailReserveConstants.BANGALORE,
				RailReserveConstants.COIMBATORE, 30, RailReserveConstants.CBE_VANDE_BHARAT,
				RailReserveConstants.SECTION_B, 3, RailReserveConstants.UB));

		sections.add(new Section(RailReserveConstants.SECTION_B, passengers));
		trainToSectionMap.put(RailReserveConstants.CBE_VANDE_BHARAT_ID, sections);

		sections = new ArrayList<>();
		passengers = new ArrayList<>();

		passengers.add(createPassenger("Michael", "Ross", "michael.ross@railreserve.com",
				RailReserveConstants.EUROSTAR_TRAIN_9014, RailReserveConstants.LONDON_ST_PANCRAS_INTERNATIONAL,
				RailReserveConstants.PARIS_NORD, 20, RailReserveConstants.EUROSTAR, RailReserveConstants.SECTION_A, 1,
				RailReserveConstants.LB));

		passengers.add(createPassenger("Patrick", "Charles", "patrick.charles@railreserve.com",
				RailReserveConstants.EUROSTAR_TRAIN_9014, RailReserveConstants.LONDON_ST_PANCRAS_INTERNATIONAL,
				RailReserveConstants.PARIS_NORD, 20, RailReserveConstants.EUROSTAR, RailReserveConstants.SECTION_A, 2,
				RailReserveConstants.MB));

		sections.add(new Section(RailReserveConstants.SECTION_A, passengers));

		passengers = new ArrayList<>();

		passengers.add(
				createPassenger("Luciana", "Silva", "luci@railreserve.com", RailReserveConstants.EUROSTAR_TRAIN_9014,
						RailReserveConstants.LONDON_ST_PANCRAS_INTERNATIONAL, RailReserveConstants.PARIS_NORD, 20,
						RailReserveConstants.EUROSTAR, RailReserveConstants.SECTION_B, 1, RailReserveConstants.LB));

		passengers.add(
				createPassenger("Samuel", "Issac", "samuel@railreserve.com", RailReserveConstants.EUROSTAR_TRAIN_9014,
						RailReserveConstants.LONDON_ST_PANCRAS_INTERNATIONAL, RailReserveConstants.PARIS_NORD, 20,
						RailReserveConstants.EUROSTAR, RailReserveConstants.SECTION_B, 2, RailReserveConstants.MB));

		sections.add(new Section(RailReserveConstants.SECTION_B, passengers));
		trainToSectionMap.put(RailReserveConstants.EUROSTAR_TRAIN_9014, sections);

		return trainToSectionMap;
	}

	@Bean
	Map<Integer, String> seatTypeMap() {
		Map<Integer, String> seatTypeMap = new HashMap<>();
		for (int i = 1; i <= RailReserveConstants.MAXIMUM_SEATING_CAPACITY; i++) {
			int remainder = i % 8;
			switch (remainder) {
			case 1, 4: {
				seatTypeMap.put(i, RailReserveConstants.LB);
				break;
			}
			case 2, 5: {
				seatTypeMap.put(i, RailReserveConstants.MB);
				break;
			}
			case 3, 6: {
				seatTypeMap.put(i, RailReserveConstants.UB);
				break;
			}
			case 7: {
				seatTypeMap.put(i, RailReserveConstants.SLB);
				break;
			}
			case 0: {
				seatTypeMap.put(i, RailReserveConstants.SUB);
				break;
			}
			default:

			}
		}
		return seatTypeMap;
	}

	public Passenger createPassenger(String firstName, String lastName, String email, int trainId, String from,
			String to, int cost, String trainName, char section, int seatNumber, String seatType) {
		return new Passenger(firstName, lastName, email, new Ticket(UUID.randomUUID().toString(), trainId, from, to,
				BigDecimal.valueOf(cost), trainName, section, new Seat(seatNumber, seatType)));
	}

}
