package org.fyp.marketplace.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "journey")
public class Journey {
	@Transient
    public static final String SEQUENCE_NAME = "journeys_sequence";

    @Id
    private long id;

	private String fromCity;
    private String destination;
    private Date insertDate = new Date();
    private Date lastUpdateDate = new Date();
    private Boolean isActive;

    private long vehicleId;
    private long orderId;

    public Journey(String fromCity, String destination, Date insertDate, Date lastUpdateDate, Boolean isActive, long vehicleId, long orderId) {
        this.fromCity = fromCity;
        this.destination = destination;
        this.insertDate = insertDate;
        this.lastUpdateDate = lastUpdateDate;
        this.isActive = isActive;
        this.vehicleId = vehicleId;
        this.orderId = orderId;
    }

    public String getFromCity() {
        return fromCity;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
