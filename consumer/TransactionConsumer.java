package org.acme.consumer;


import org.acme.model.Transaction;
import org.acme.producer.FinalProducer;
import org.eclipse.microprofile.reactive.messaging.Incoming;
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

    @Incoming("confirmation-in")
    //@Outgoing("transaction-finished-out")
    public void process(Transaction transaction) {
        if(transaction!=null) {
            //logger.info("ToString" + transaction.toString());
            logger.info("Got an Authorizations for transaction: " + transaction.toString());
            //logger.info("Got an Authorizations for transaction:" + transaction.value());
            //producer.sendTransactionToKafka(transaction.value());
            producer.sendTransactionToKafka(transaction);
            //logger.info("Record" + transaction);
            //logger.info("Record" + transaction.withKey("52384590238450982349508203580345"));
            //logger.infof("Got an Authorizations for transaction: %d - %s", transaction.key(), transaction.value());
        }else
            logger.info("Topic naked!" );
    }

//    @Incoming("process_transaction")
//
//    public Transaction process(Transaction transaction) {
//        logger.info("Got a transaction: " + transaction.id);
}