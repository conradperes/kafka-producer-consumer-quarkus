package org.acme;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Properties;


@ApplicationScoped
public class FinalProducer {

    private final Logger logger = Logger.getLogger(FinalProducer.class);

    public static final String VALIDATION_BALANCE = "VALIDATION_BALANCE";
    public static final String VALIDATION_PIN = "VALIDATION_PIN";
    private static final String REQUEST = "REQUEST";
    private static final String CANCEL = "CANCEL";
    protected static final String PAYMENT = "payment";
    protected static final String SUCCEEDED = "SUCCEEDED";
    @Inject
    @Channel("transaction-finished-out")
    Emitter< Transaction> emitter;


    public void sendTransactionToKafka(Transaction transaction) {
        if(transaction!=null) {
            if (    transaction.stepstatus.equalsIgnoreCase(SUCCEEDED) &&
                    transaction.sagastatus.equalsIgnoreCase(SUCCEEDED)) {
                emitter.send(transaction);
                logger.info("Transaction Succeeded=" + transaction);
            } else {
                emitter.send(transaction);
                logger.info("Transaction Rejected=" + transaction);
            }
        }
    }


//    static KafkaStreams createStreams(final Properties streamsConfiguration) {
//        final Serde<String> stringSerde = Serdes.String();
//        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, String>
//                textLines = builder.stream(TEXT_LINES_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));
//        final KGroupedStream<String, String> groupedByWord = textLines
//                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
//                .groupBy((key, word) -> word, Serialized.with(stringSerde, stringSerde));
//        // Create a State Store for with the all time word count
//        groupedByWord.count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("word-count")
//                .withValueSerde(Serdes.Long()));
//        // Create a Windowed State Store that contains the word count for every
//        // 1 minute
//        groupedByWord.windowedBy(TimeWindows.of(60000))
//                .count(Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("windowed-word-count")
//                        .withValueSerde(Serdes.Long()));
//        return new KafkaStreams(builder.build(), streamsConfiguration);
//    }
}
