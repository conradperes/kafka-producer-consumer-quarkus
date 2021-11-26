package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionConsumer {

    private final Logger logger = Logger.getLogger(TransactionConsumer.class);

    @Incoming("movies-in")
    public void process(Record<Integer, Transaction> record) {
        if(record!=null) {
            //logger.info("ToString" + record.toString());
            logger.info("Got an Authorizations for record: " + record.key());
            //logger.info("Value" + record.value());
            //logger.info("Record" + record);
            //logger.info("Record" + record.withKey("52384590238450982349508203580345"));
            //logger.infof("Got an Authorizations for record: %d - %s", record.key(), record.value());
        }else
            logger.info("Topic naked!" );
    }

//    @Incoming("process_transaction")
//
//    public Transaction process(Transaction transaction) {
//        logger.info("Got a transaction: " + transaction.id);
}
