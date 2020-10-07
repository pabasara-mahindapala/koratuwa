package org.fyp.marketplace.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "category")
public class Category {
	@Id
	@Field(value = "_id")
	private ObjectId _id;

	private Date insertDate;
	private Date lastUpdateDate;
	private ObjectId subCategoryId;
	private String categoryName;

	public Category(ObjectId _id, Date insertDate, Date lastUpdateDate, ObjectId subCategoryId, String categoryName) {
		this._id = _id;
		this.insertDate = insertDate;
		this.lastUpdateDate = lastUpdateDate;
		this.subCategoryId = subCategoryId;
		this.categoryName = categoryName;
	}

	public ObjectId get_id() {
		return _id;
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

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public ObjectId getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(ObjectId subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
