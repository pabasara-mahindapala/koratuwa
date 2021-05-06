package lk.agrohub.market.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import lk.agrohub.market.model.Product;
import lk.agrohub.market.model.Vehicle;
import lk.agrohub.market.repository.VehicleRepository;

import java.util.Date;
import java.util.List;

@Service
public class VehicleService {

	final VehicleRepository vehicleRepository;

	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	public Vehicle vehiclesSearchById(long _id) {
		return vehicleRepository.findById(_id).get();
	}

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
