package org.fyp.marketplace.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "product")
public class Product {
	@Transient
    public static final String SEQUENCE_NAME = "products_sequence";

    @Id
    private long id;

	private String productName;
    private Date insertDate;
    private Date lastUpdateDate;
    private double unitPrice;
    private double amount;
    private Date availableDate;
    private String cultivationMethod;
    private String location;
    private long producerId;
    private long categoryId;
    private long subCategoryId;

    public Product(String productName, Date insertDate, Date lastUpdateDate, double unitPrice, double amount, Date availableDate, String cultivationMethod, String location, long producerId, long categoryId, long subCategoryId) {
        this.productName = productName;
        this.insertDate = insertDate;
        this.lastUpdateDate = lastUpdateDate;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.availableDate = availableDate;
        this.cultivationMethod = cultivationMethod;
        this.location = location;
        this.producerId = producerId;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    

    public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	public String getCultivationMethod() {
        return cultivationMethod;
    }

    public void setCultivationMethod(String cultivationMethod) {
        this.cultivationMethod = cultivationMethod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProducerId() {
		return producerId;
	}

	public void setProducerId(long producerId) {
		this.producerId = producerId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

    

}
