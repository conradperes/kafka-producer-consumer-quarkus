package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

@ApplicationScoped
public class TransactionProducer {
    private final Logger logger = Logger.getLogger(TransactionProducer.class);



    @Inject @Channel("movies-out")
    Emitter<Record<Integer, Transaction>> emitter;


    public void sendTransactionToKafka(Transaction transaction) {
        UUID uuid = UUID.randomUUID();
        transaction.id = uuid.toString();
        transaction.payload = Long.toString(new Date().getTime());
        logger.info("Producer of transacion="+transaction);
        emitter.send(Record.of(transaction.id.hashCode(), transaction));
    }
}
