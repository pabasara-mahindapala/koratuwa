package org.fyp.marketplace.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "vehicle")
public class Vehicle {
	@Transient
    public static final String SEQUENCE_NAME = "vehicles_sequence";

    @Id
    private long id;

	private String vehicleNumber;
    private String vehicleType;
    private int capacity;
    private Date insertDate;
    private Date lastUpdateDate;

    public Vehicle(String vehicleNumber, String vehicleType, int capacity, Date insertDate, Date lastUpdateDate) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
        this.insertDate = insertDate;
        this.lastUpdateDate = lastUpdateDate;
    }


    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}

