package org.acme.topology;

import org.acme.model.JsonSerde;
import org.acme.model.Transaction;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

import java.util.stream.Stream;


public class CreditTransactionTopology {

    static final String TRANSACTION_FINISHED_TOPIC = "transaction-finished";
    static final String CONFIRMATION_AUTHORIZATION_TOPIC = "confirmation-authorization";
    public static final String SUCEEDEED = "SUCEEDEED";
    public static final String BANK_TRANSACTIONS = "bank-transactions";
    public static final String BANK_BALANCES = "bank-balances";
    public static final String REJECTED_TRANSACTIONS = "rejected-transactions";
    public static void groupMessages(){
//        final StreamsBuilder builder = new StreamsBuilder();
//        KStream<Void, Transaction> source = builder.stream(CONFIRMATION_AUTHORIZATION_TOPIC);
//        final KStream<TickerWindow, Transaction> sumOfOddNumbers = source
//                // We are only interested in odd numbers.
//                //.filter((k, v) -> v.sagastatus.equals(SUCEEDEED) && v.stepstatus.equals(SUCEEDEED))
//                // We want to compute the total sum across ALL numbers, so we must re-key all records to the
//                // same key.  This re-keying is required because in Kafka Streams a data record is always a
//                // key-value pair, and KStream aggregations such as `reduce` operate on a per-key basis.
//                // The actual new key (here: `1`) we pick here doesn't matter as long it is the same across
//                // all records.
//                //.selectKey((k, v) -> 1)
//                // no need to specify explicit serdes because the resulting key and value types match our default serde settings
//                .groupByKey()
//                .aggregate(Transaction::new,
//                        (x,y, transaction) -> transaction.update(v),
//                        TimeWindows.of(50).advanceBy(10),
//                        new Transaction(),
//                        CONFIRMATION_AUTHORIZATION_TOPIC)
//                .toStream((key, value) -> TickerWindow(key.key(),
//                        key.window().start()))
//                .mapValues((transaction)->transaction.update(transaction));
//                // Add the numbers to compute the sum.
//                .reduce((v1, v2) -> v1.update(v2));
//
//        sumOfOddNumbers.toStream().to(TRANSACTION_FINISHED_TOPIC);
    }

//    public static void aggregateMessages(String topic, String topic2){
//
//        final String parentTopic = topic;
//        final String childrenTopic = topic2;
//
//
//        final Serde<DefaultId> defaultIdSerde = SerdeFactory.createDbzEventJsonPojoSerdeFor(DefaultId.class,true);
//        final Serde<Transaction> transactionSerde = SerdeFactory.createDbzEventJsonPojoSerdeFor(Transaction.class,true);
////        final Serde<Customer> customerSerde = SerdeFactory.createDbzEventJsonPojoSerdeFor(Customer.class,false);
////        final Serde<Address> addressSerde = SerdeFactory.createDbzEventJsonPojoSerdeFor(Address.class,false);
////        final Serde<LatestAddress> latestAddressSerde = SerdeFactory.createDbzEventJsonPojoSerdeFor(LatestAddress.class,false);
////        final Serde<Addresses> addressesSerde = SerdeFactory.createDbzEventJsonPojoSerdeFor(Addresses.class,false);
////        final Serde<CustomerAddressAggregate> aggregateSerde =
////                SerdeFactory.createDbzEventJsonPojoSerdeFor(CustomerAddressAggregate.class,false);
//
//        StreamsBuilder builder = new StreamsBuilder();
//
//        //1) read parent topic i.e. customers as ktable
//        KTable<DefaultId, Transaction> customerTable =
//                builder.table(parentTopic, Consumed.with(defaultIdSerde, transactionSerde));
//
//        //2) read children topic i.e. addresses as kstream
//        KStream<DefaultId, Transaction> addressStream = builder.stream(childrenTopic,
//                Consumed.with(defaultIdSerde, transactionSerde));
//
//        //2a) pseudo-aggreate addresses to keep latest relationship info
//        KTable<DefaultId,Transaction> tempTable = addressStream
//                .groupByKey(Serialized.with(defaultIdSerde, transactionSerde))
//                .aggregate(
//                        () -> new Transaction(),
//                        (DefaultId addressId, Transaction latest) -> {
//                            latest.update(latest);
//                            return latest;
//                        },
//                        Materialized.<DefaultId,Transaction, KeyValueStore<Bytes, byte[]>>
//                                as(childrenTopic+"_table_temp")
//                                .withKeySerde(defaultIdSerde)
//                                .withValueSerde(transactionSerde)
//                );
//
//        //2b) aggregate addresses per customer id
//        KTable<DefaultId, Addresses> addressTable = tempTable.toStream()
//                .map((addressId, latestAddress) -> new KeyValue<>(latestAddress.getCustomerId(),latestAddress))
//                .groupByKey(Serialized.with(defaultIdSerde,latestAddressSerde))
//                .aggregate(
//                        () -> new Addresses(),
//                        (customerId, latestAddress, addresses) -> {
//                            addresses.update(latestAddress);
//                            return addresses;
//                        },
//                        Materialized.<DefaultId,Addresses,KeyValueStore<Bytes, byte[]>>
//                                as(childrenTopic+"_table_aggregate")
//                                .withKeySerde(defaultIdSerde)
//                                .withValueSerde(addressesSerde)
//                );
//
//        //3) KTable-KTable JOIN to combine customer and addresses
//        KTable<DefaultId,CustomerAddressAggregate> dddAggregate =
//                customerTable.join(addressTable, (customer, addresses) ->
//                        customer.get_eventType() == EventType.DELETE ?
//                                null : new CustomerAddressAggregate(customer,addresses.getEntries())
//                );
//
//        dddAggregate.toStream().to("final_ddd_aggregates",
//                Produced.with(defaultIdSerde,(Serde)aggregateSerde));
//
//        dddAggregate.toStream().print(toSysOut());
//
//        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
//        streams.start();
//
//        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
//
//
//
//    }


//    public static Topology buildTopology() {
//        Serde<Transaction> bankTransactionSerdes = new JsonSerde<>(Transaction.class);
//        Serde<Transaction> bankBalanceSerde = new JsonSerde<>(Transaction.class);
//        Stream.groupByKey()
//                .aggregate(Transaction::new,
//                        (key, value, aggregate) -> aggregate.process(value),
//                        Materialized.with(Serdes.Long(), bankBalanceSerde))
//                .toStream();
//        bankBalancesStream
//                .to(BANK_BALANCES, Produced.with(Serdes.Long(), bankBalanceSerde));
//
//        bankBalancesStream
//                .msBuilder streamsBuilder = new StreamsBuilder();
//
//        KStream<Long, Transaction> bankBalancesStream = streamsBuilder.stream(BANK_TRANSACTIONS,
//                Consumed.with(Serdes.Long(), bankTransactionSerdes))
//        apValues((readOnlyKey, value) -> value.getLatestTransaction())
//                .filter((key, value) -> value.state == BankTransaction.BankTransactionState.REJECTED)
//                .to(REJECTED_TRANSACTIONS, Produced.with(Serdes.Long(), bankTransactionSerdes));
//
//        return streamsBuilder.build();
//    }
    public static Topology buildTopology() {
        final StreamsBuilder builder = new StreamsBuilder();
        // We assume the input topic contains records where the values are Integers.
        // We don't really care about the keys of the input records;  for simplicity, we assume them
        // to be Integers, too, because we will re-key the stream later on, and the new key will be
        // of type Integer.

        final KStream<Void, Transaction> input = builder.stream(CONFIRMATION_AUTHORIZATION_TOPIC);

        final KTable<Integer, Transaction> sumOfOddNumbers = input
                // We are only interested in odd numbers.
                .filter((k, v) -> v.sagastatus.equals(SUCEEDEED) && v.stepstatus.equals(SUCEEDEED))
                // We want to compute the total sum across ALL numbers, so we must re-key all records to the
                // same key.  This re-keying is required because in Kafka Streams a data record is always a
                // key-value pair, and KStream aggregations such as `reduce` operate on a per-key basis.
                // The actual new key (here: `1`) we pick here doesn't matter as long it is the same across
                // all records.
                .selectKey((k, v) -> 1)
                // no need to specify explicit serdes because the resulting key and value types match our default serde settings
                .groupByKey()
                // Add the numbers to compute the sum.
                .reduce((v1, v2) -> v1.update(v2));

        sumOfOddNumbers.toStream().to(TRANSACTION_FINISHED_TOPIC);

        return builder.build();
    }

}
