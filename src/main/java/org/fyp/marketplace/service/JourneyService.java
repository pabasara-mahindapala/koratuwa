package org.fyp.marketplace.service;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Journey;
import org.fyp.marketplace.repository.JourneyRepository;
import org.fyp.marketplace.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JourneyService {
    final JourneyRepository journeyRepository;
    final OrderRepository orderRepository;

    public JourneyService(JourneyRepository journeyRepository, OrderRepository orderRepository) {
        this.journeyRepository=journeyRepository;
        this.orderRepository=orderRepository;
    }

    public List<Journey> getAllJourneys() {
        return journeyRepository.findAll();
    }

    public Journey journeySearchById(ObjectId _id){return journeyRepository.findBy_id(_id);}

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
