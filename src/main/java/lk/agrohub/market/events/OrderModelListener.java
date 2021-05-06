package org.fyp.marketplace.events;

import org.fyp.marketplace.model.Order;
import org.fyp.marketplace.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderModelListener extends AbstractMongoEventListener<Order> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public OrderModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Order> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Order.SEQUENCE_NAME));
        }
    }


}