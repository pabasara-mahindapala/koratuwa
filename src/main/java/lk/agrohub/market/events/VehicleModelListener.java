package org.fyp.marketplace.events;

import org.fyp.marketplace.model.Stock;
import org.fyp.marketplace.model.Vehicle;
import org.fyp.marketplace.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class VehicleModelListener extends AbstractMongoEventListener<Vehicle> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public VehicleModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Vehicle> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Vehicle.SEQUENCE_NAME));
        }
    }


}