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
    private String cultivationMethod;
    private long categoryId;
    private long subCategoryId;

    public Product(String productName, Date insertDate, Date lastUpdateDate, String cultivationMethod, long categoryId,
			long subCategoryId) {
		super();
		this.productName = productName;
		this.insertDate = insertDate;
		this.lastUpdateDate = lastUpdateDate;
		this.cultivationMethod = cultivationMethod;
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

	public String getCultivationMethod() {
        return cultivationMethod;
    }

    public void setCultivationMethod(String cultivationMethod) {
        this.cultivationMethod = cultivationMethod;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
