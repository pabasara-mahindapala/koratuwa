package org.fyp.marketplace.events;

import org.fyp.marketplace.model.Journey;
import org.fyp.marketplace.model.Stock;
import org.fyp.marketplace.model.Vehicle;
import org.fyp.marketplace.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class JourneyModelListener extends AbstractMongoEventListener<Journey> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public JourneyModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Journey> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Journey.SEQUENCE_NAME));
        }
    }


}