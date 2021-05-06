package lk.agrohub.market.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lk.agrohub.market.model.Vehicle;
import lk.agrohub.market.service.VehicleService;

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
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    public List<Vehicle> listAllVehicle() {
        try {
            return this.vehicleService.getAllVehicles();
        } catch (Exception e) {
            // Log error
            return new ArrayList<Vehicle>();
        }
    }

    @GetMapping("/{vehicleId}")
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    public Vehicle getVehicle(@PathVariable long vehicleId) {

        Vehicle vehicle = vehicleService.vehiclesSearchById(vehicleId);

        // throw exception if null

        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }

        return vehicle;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('TRANSPORTER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('TRANSPORTER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('TRANSPORTER') or hasRole('ADMIN')")
    public String deleteVehicle(@PathVariable long vehicleId) {

        Vehicle vehicle = vehicleService.vehiclesSearchById(vehicleId);

        // throw exception if null

        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }

        vehicleService.deleteVehicle(vehicle);

        return "Deleted Vehicle : " + vehicle.getVehicleNumber();
    }
}
