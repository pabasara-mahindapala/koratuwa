package org.fyp.marketplace.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "vehicle")
public class Vehicle {
    @Id
    @Field(value = "_id")
    private ObjectId _id;
    private String vehicleNumber;
    private String vehicleType;
    private int capacity;
    private Date insertDate;
    private Date lastUpdateDate;

    public Vehicle(ObjectId _id, String vehicleNumber, String vehicleType, int capacity, Date insertDate, Date lastUpdateDate) {
        this._id = _id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
        this.insertDate = insertDate;
        this.lastUpdateDate = lastUpdateDate;
    }


    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
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

