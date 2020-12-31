package org.fyp.marketplace.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journeyDetail")
public class JourneyDetailPK {

    private int journeyId;
    private int orderId;

    public JourneyDetailPK(int journeyId, int orderId) {
        this.journeyId = journeyId;
        this.orderId = orderId;
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
