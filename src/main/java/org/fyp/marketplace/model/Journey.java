package org.fyp.marketplace.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "journey")
public class Journey {
    @Id
    @Field(value = "_id")
    private ObjectId _id;
    private String fromCity;
    private String destination;
    private Date insertDate = new Date();
    private Date lastUpdateDate = new Date();
    private Boolean isActive;

    private ObjectId vehicleId;
    private ObjectId orderId;

    public Journey(ObjectId _id, String fromCity, String destination, Date insertDate, Date lastUpdateDate, Boolean isActive, ObjectId vehicleId, ObjectId orderId) {
        this._id = _id;
        this.fromCity = fromCity;
        this.destination = destination;
        this.insertDate = insertDate;
        this.lastUpdateDate = lastUpdateDate;
        this.isActive = isActive;
        this.vehicleId = vehicleId;
        this.orderId = orderId;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ObjectId getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(ObjectId vehicleId) {
        this.vehicleId = vehicleId;
    }

    public ObjectId getOrderId() {
        return orderId;
    }

    public void setOrderId(ObjectId orderId) {
        this.orderId = orderId;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
