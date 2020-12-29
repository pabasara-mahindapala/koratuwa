package org.fyp.marketplace.controller;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Vehicle;
import org.fyp.marketplace.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/vehicle")
public class VehicleController {
    final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        super();
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public List<Vehicle> listAllVehicle() {
        try {
            return this.vehicleService.getAllVehicles();
        } catch (Exception e) {
            // Log error
            return new ArrayList<Vehicle>();
        }
    }

    @GetMapping("/{vehicleId}")
    public Vehicle getVehicle(@PathVariable ObjectId vehicleId) {

        Vehicle vehicle = vehicleService.vehiclesSearchById(vehicleId);

        // throw exception if null

        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }

        return vehicle;
    }

    @PostMapping("/add")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) throws Exception {
        ResponseEntity<Vehicle> result;
        try {
            this.vehicleService.addVehicle(vehicle);
            result = new ResponseEntity<>(vehicle, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(vehicle, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/update")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) throws Exception {
        ResponseEntity<Vehicle> result;
        try {
            this.vehicleService.updateVehicle(vehicle);
            result = new ResponseEntity<>(vehicle, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(vehicle, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("/delete/{vehicleId}")
    public String deleteVehicle(@PathVariable ObjectId vehicleId) {

        Vehicle vehicle = vehicleService.vehiclesSearchById(vehicleId);

        // throw exception if null

        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }

        vehicleService.deleteVehicle(vehicle);

        return "Deleted Vehicle : " + vehicle.getVehicleNumber();
    }
}