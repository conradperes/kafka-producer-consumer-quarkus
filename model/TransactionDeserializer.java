package org.acme.model;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.model.Transaction;

public class TransactionDeserializer extends ObjectMapperDeserializer<Transaction> {
    public TransactionDeserializer() {
        super(Transaction.class);
    }

}