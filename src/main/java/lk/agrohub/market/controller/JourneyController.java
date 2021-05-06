package lk.agrohub.market.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lk.agrohub.market.model.Journey;
import lk.agrohub.market.service.JourneyService;

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
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    public List<Journey> listAllJourney() {
        try {
            return this.journeyService.getAllJourneys();
        } catch (Exception e) {
            // Log error
            return new ArrayList<Journey>();
        }
    }

    @GetMapping("/{journeyId}")
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    public Journey getJourney(@PathVariable long journeyId) {

        Journey journey = journeyService.journeySearchById(journeyId);

        // throw exception if null

        if (journey == null) {
            throw new RuntimeException("Journey not found");
        }

        return journey;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('TRANSPORTER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('TRANSPORTER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('TRANSPORTER') or hasRole('ADMIN')")
    public String deleteJourney(@PathVariable long journeyId) {

        Journey journey = journeyService.journeySearchById(journeyId);

        // throw exception if null

        if (journey == null) {
            throw new RuntimeException("Journey not found");
        }

        journeyService.deleteVJourney(journey);

        return "Deleted Journey : ";
    }
}
