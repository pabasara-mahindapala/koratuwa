package org.fyp.marketplace.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cart")


public class CartItem {

    @Id
    @Field(value = "_id")
    private ObjectId _id;

    private Boolean isActive;
    private ObjectId customerId;
    private Integer quanity;
    private ObjectId productId;

    public CartItem(ObjectId _id, Boolean isActive, ObjectId customerId, Integer quanity, ObjectId productId) {
        this._id = _id;
        this.isActive = isActive;
        this.customerId = customerId;
        this.quanity = quanity;
        this.productId = productId;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public ObjectId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(ObjectId customerId) {
        this.customerId = customerId;
    }

    public Integer getQuanity() {
        return quanity;
    }

    public void setQuanity(Integer quanity) {
        this.quanity = quanity;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }
}
