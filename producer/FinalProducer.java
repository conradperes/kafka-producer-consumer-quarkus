package org.acme.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.model.Transaction;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
//import org.apache.kafka.streams.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;

import static org.acme.topology.CreditTransactionTopology.*;


@ApplicationScoped
public class FinalProducer {

    private final Logger logger = Logger.getLogger(FinalProducer.class);
    static final String TRANSACTION_FINISHED_TOPIC = "transaction-finished";
    public static final String VALIDATION_BALANCE = "VALIDATION_BALANCE";
    public static final String VALIDATION_PIN = "VALIDATION_PIN";
    private static final String REQUEST = "REQUEST";
    private static final String CANCEL = "CANCEL";
    protected static final String PAYMENT = "payment";
    protected static final String SUCEEDEED = "SUCEEDEED";
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Inject
    @Channel("transaction-finished-out")
    Emitter<Transaction> emitter;


    public void sendTransactionToKafka(Transaction transaction) {
        logger.info("Final Producer sending last message="+transaction.toString());
        //JsonNode jsonNode;
        try {
            if (transaction != null) {
                if (transaction.stepstatus.equals(SUCEEDEED) &&
                        transaction.sagastatus.equals(SUCEEDEED)) {
                    emitter.send(transaction);
                    buildTopology();
                    logger.info("Transaction Succeeded=" + transaction.id);
                } else {
                    emitter.send(transaction);
                    buildTopology();
                    logger.info("Transaction Processed with error=" + transaction.id);
                }
            }else{
                logger.info("Empty Record!##### Not sent on the next topic!");
            }
        }catch (Exception e){
            logger.info("Exception occurred"+e.getMessage());
        }
    }


    static KafkaStreams createStreams(final Properties streamsConfiguration) {
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Transaction> transactionSerde = Serdes.serdeFrom(Transaction.class);
        Serializer<String> serializer = Serdes.String().serializer();
        Deserializer<Transaction>deserializer = Serdes.serdeFrom(Transaction.class).deserializer();


        StreamsBuilder builder = new StreamsBuilder();
        KStream<String,  Transaction>
                streamTransaction = builder.stream(TRANSACTION_FINISHED_TOPIC, Consumed.with(stringSerde, transactionSerde));
//        final KGroupedStream<Transaction, String> groupedByTransaction = streamTransaction
//                .<Transaction>flatMapValues(value -> () -> (Iterator<Transaction>) value)
//                .groupBy((key, word) -> word, Serialized.with(stringSerde, stringSerde));
        // Create a State Store for with the all time word count
//        groupedByTransaction.aggregate(() -> "",
//                (id, stepstatus, sagastatus) -> sagastatus + stepstatus,
//                Materialized.with(Serdes.String(), Serdes.String()));
//        //groupedByTransaction.count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("word-count")
//        //        .withValueSerde(Serdes.Long()));
//        // Create a Windowed State Store that contains the word count for every
//        // 1 minute
//        groupedByTransaction.windowedBy(TimeWindows.of(60000))
//                .count(Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("windowed-word-count")
//                        .withValueSerde(Serdes.Long()));
        return new KafkaStreams(builder.build(), streamsConfiguration);
    }
}