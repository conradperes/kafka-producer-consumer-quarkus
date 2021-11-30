package org.acme;


import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TransactionConsumer {

    public static final String VALIDATION_BALANCE = "VALIDATION_BALANCE";
    public static final String VALIDATION_PIN = "VALIDATION_PIN";
    private final Logger logger = Logger.getLogger(TransactionConsumer.class);
    private static final String REQUEST = "REQUEST";
    private static final String CANCEL = "CANCEL";
    protected static final String PAYMENT = "payment";
    protected static final String CREDIT_APPROVAL = "SUCCEEDED";


    @Inject
    FinalProducer producer;

    @Incoming("movies-in")
    //@Outgoing("transaction-finished-out")
    public void process(Record<Integer, Transaction> record) {
        if(record!=null) {
            //logger.info("ToString" + record.toString());
            //logger.info("Got an Authorizations for record: " + record.key());
            logger.info("Got an Authorizations for record:" + record.value());
            producer.sendTransactionToKafka(record.value());

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