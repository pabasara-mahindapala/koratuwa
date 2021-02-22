package org.fyp.marketplace.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "stock")
public class Stock {
	@Transient
    public static final String SEQUENCE_NAME = "stocks_sequence";

    @Id
    private long id;

	private Date insertDate;
    private Date lastUpdateDate;
	private Boolean isActive;
	private long productId;
	private long producerId;
	private String quantityUnit;
	private Integer quantity;
	private String quality;
	private Float pricePerUnit;
	
	public Stock(Date insertDate, Date lastUpdateDate, Boolean isActive, long productId, long producerId,
			String quantityUnit, Integer quantity, String quality, Float pricePerUnit) {
		super();
		this.insertDate = insertDate;
		this.lastUpdateDate = lastUpdateDate;
		this.isActive = isActive;
		this.productId = productId;
		this.producerId = producerId;
		this.quantityUnit = quantityUnit;
		this.quantity = quantity;
		this.quality = quality;
		this.pricePerUnit = pricePerUnit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getProducerId() {
		return producerId;
	}

	public void setProducerId(long producerId) {
		this.producerId = producerId;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public Float getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	

}
