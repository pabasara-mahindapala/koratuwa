package org.fyp.marketplace.events;

import org.fyp.marketplace.model.Category;
import org.fyp.marketplace.model.User;
import org.fyp.marketplace.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class CategoryModelListener extends AbstractMongoEventListener<Category> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public CategoryModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Category> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Category.SEQUENCE_NAME));
        }
    }


}