package lk.agrohub.market.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import lk.agrohub.market.model.Stock;
import lk.agrohub.market.service.SequenceGeneratorService;

@Component
public class StockModelListener extends AbstractMongoEventListener<Stock> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public StockModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Stock> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Stock.SEQUENCE_NAME));
        }
    }


}