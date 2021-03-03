package org.fyp.marketplace.events;

import org.fyp.marketplace.model.Review;
import org.fyp.marketplace.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class ReviewModelListener extends AbstractMongoEventListener<Review> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public ReviewModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Review> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Review.SEQUENCE_NAME));
        }
    }


}