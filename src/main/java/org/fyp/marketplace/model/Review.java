package org.fyp.marketplace.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "review")
public class Review {
	@Id
	@Field(value = "_id")
	private ObjectId _id;
	
	private ObjectId reviewerId;
	private String reviewBody;
	
	private Date insertDate;
	private Integer rating;
	
	public Review(ObjectId _id, ObjectId reviewerId, String reviewBody, Date insertDate, Integer rating) {
		super();
		this._id = _id;
		this.reviewerId = reviewerId;
		this.reviewBody = reviewBody;
		this.insertDate = insertDate;
		this.rating = rating;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public ObjectId getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(ObjectId reviewerId) {
		this.reviewerId = reviewerId;
	}
	public String getReviewBody() {
		return reviewBody;
	}
	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
	
}
