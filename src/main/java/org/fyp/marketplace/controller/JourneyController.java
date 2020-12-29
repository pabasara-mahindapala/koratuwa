package org.fyp.marketplace.controller;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Journey;
import org.fyp.marketplace.service.JourneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/journey")
public class JourneyController {
    final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        super();
        this.journeyService = journeyService;
    }

    @GetMapping("/all")
    public List<Journey> listAllJourney() {
        try {
            return this.journeyService.getAllJourneys();
        } catch (Exception e) {
            // Log error
            return new ArrayList<Journey>();
        }
    }

    @GetMapping("/{journeyId}")
    public Journey getJourney(@PathVariable ObjectId journeyId) {

        Journey journey = journeyService.journeySearchById(journeyId);

        // throw exception if null

        if (journey == null) {
            throw new RuntimeException("Journey not found");
        }

        return journey;
    }

    @PostMapping("/add")
    public ResponseEntity<Journey> createJourney(@RequestBody Journey journey) throws Exception {
        ResponseEntity<Journey> result;
        try {
            this.journeyService.createJourney(journey);
            result = new ResponseEntity<>(journey, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(journey, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/update")
    public ResponseEntity<Journey> updateJourney(@RequestBody Journey journey) throws Exception {
        ResponseEntity<Journey> result;
        try {
            this.journeyService.updateJourney(journey);
            result = new ResponseEntity<>(journey, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(journey, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("/delete/{journeyId}")
    public String deleteJourney(@PathVariable ObjectId journeyId) {

        Journey journey = journeyService.journeySearchById(journeyId);

        // throw exception if null

        if (journey == null) {
            throw new RuntimeException("Journey not found");
        }

        journeyService.deleteVJourney(journey);

        return "Deleted Journey : ";
    }
}
