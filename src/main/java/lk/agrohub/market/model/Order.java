/**
 * 
 */
package lk.agrohub.market.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "order")
public class Order implements Serializable {
	@Transient
	public static final String SEQUENCE_NAME = "orders_sequence";

	@Id
	private long id;

	private Date insertDate = new Date();
	private Boolean isActive;
	private long productId;
	private long producerId;
	private long customerId;
	private Integer quanity;

	public Order(Date insertDate, Boolean isActive, long productId, long producerId, long customerId, Integer quanity) {
		super();
		this.insertDate = insertDate;
		this.isActive = isActive;
		this.productId = productId;
		this.producerId = producerId;
		this.customerId = customerId;
		this.quanity = quanity;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getQuanity() {
		return quanity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public void setQuanity(Integer quanity) {
		this.quanity = quanity;
	}

}
