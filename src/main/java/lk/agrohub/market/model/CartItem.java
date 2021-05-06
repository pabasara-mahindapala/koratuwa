package lk.agrohub.market.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cart")


public class CartItem {

	@Transient
    public static final String SEQUENCE_NAME = "cartitems_sequence";

    @Id
    private long id;

	private Boolean isActive;
    private long customerId;
    private Integer quanity;
    private long productId;

    public CartItem(Boolean isActive, long customerId, Integer quanity, long productId) {
        this.isActive = isActive;
        this.customerId = customerId;
        this.quanity = quanity;
        this.productId = productId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getQuanity() {
        return quanity;
    }

    public void setQuanity(Integer quanity) {
        this.quanity = quanity;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
    
    
}
