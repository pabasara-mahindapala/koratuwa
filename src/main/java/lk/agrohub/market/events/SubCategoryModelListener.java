package org.fyp.marketplace.events;

import org.fyp.marketplace.model.SubCategory;
import org.fyp.marketplace.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryModelListener extends AbstractMongoEventListener<SubCategory> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public SubCategoryModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<SubCategory> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(SubCategory.SEQUENCE_NAME));
        }
    }


}