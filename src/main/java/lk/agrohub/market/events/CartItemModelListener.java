package org.fyp.marketplace.events;

import org.fyp.marketplace.model.CartItem;
import org.fyp.marketplace.model.Category;
import org.fyp.marketplace.model.User;
import org.fyp.marketplace.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class CartItemModelListener extends AbstractMongoEventListener<CartItem> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public CartItemModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<CartItem> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(CartItem.SEQUENCE_NAME));
        }
    }


}