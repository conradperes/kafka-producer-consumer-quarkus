package org.acme;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class TransactionDeserializer extends ObjectMapperDeserializer<Transaction> {
    public TransactionDeserializer() {
        super(Transaction.class);
    }

}
