package lk.agrohub.market.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import lk.agrohub.market.model.Journey;
import lk.agrohub.market.repository.JourneyRepository;
import lk.agrohub.market.repository.OrderRepository;

import java.util.Date;
import java.util.List;

@Service
public class JourneyService {
	final JourneyRepository journeyRepository;
	final OrderRepository orderRepository;

	public JourneyService(JourneyRepository journeyRepository, OrderRepository orderRepository) {
		this.journeyRepository = journeyRepository;
		this.orderRepository = orderRepository;
	}

	public List<Journey> getAllJourneys() {
		return journeyRepository.findAll();
	}

	public Journey journeySearchById(long _id) {
		return journeyRepository.findById(_id).get();
	}

	public Journey createJourney(Journey journey) throws Exception {
		journey.setIsActive(true);
		journey.setInsertDate(new Date());
		Journey newJourney = this.journeyRepository.save(journey);
		return newJourney;
	}

	public Journey updateJourney(Journey journey) {
		journey.setLastUpdateDate(new Date());
		return this.journeyRepository.save(journey);
	}

	public void deleteVJourney(Journey journey) {
		this.journeyRepository.delete(journey);
	}

}
