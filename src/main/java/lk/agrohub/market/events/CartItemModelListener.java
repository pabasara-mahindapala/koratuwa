package lk.agrohub.market.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import lk.agrohub.market.model.CartItem;
import lk.agrohub.market.model.Category;
import lk.agrohub.market.model.User;
import lk.agrohub.market.service.SequenceGeneratorService;

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