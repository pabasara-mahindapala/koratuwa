package org.fyp.marketplace.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "subCategory")
public class SubCategory {
    @Id
    @Field(value = "_id")
    private ObjectId _id;

    private Date insertDate;
    private Date lastUpdateDate;
    private ObjectId categoryId;
    private String subCategoryName;


    public SubCategory(ObjectId _id, Date insertDate, Date lastUpdateDate, ObjectId categoryId, String subCategoryName) {
        this._id = _id;
        this.insertDate = insertDate;
        this.lastUpdateDate = lastUpdateDate;
        this.categoryId = categoryId;
        this.subCategoryName = subCategoryName;
    }

    public ObjectId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ObjectId categoryId) {
        this.categoryId = categoryId;
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

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
