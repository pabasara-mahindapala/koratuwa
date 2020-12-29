package org.fyp.marketplace.service;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.model.Vehicle;
import org.fyp.marketplace.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VehicleService {

    final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository=vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle vehiclesSearchById(ObjectId _id){return vehicleRepository.findBy_id(_id);}


    public Vehicle addVehicle(Vehicle vehicle) {
        Vehicle newVehicle = this.vehicleRepository.save(vehicle);
        return newVehicle;
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        vehicle.setLastUpdateDate(new Date());
        return this.vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle) {
        this.vehicleRepository.delete(vehicle);
    }
}