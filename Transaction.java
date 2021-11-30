package org.acme;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.Serializable;

public class Transaction {

    public String id;//uui
    public String currentstep;
    public Card payload;
    public String sagastatus;
    public String stepstatus;
    public String type;
    public String version;



    public Transaction() {
    }

    public Transaction(String id, String currentstep, Card payload, String sagastatus, String stepstatus, String type, String version) {
        this.id = id;
        this.currentstep = currentstep;
        this.payload = payload;
        this.sagastatus = sagastatus;
        this.stepstatus = stepstatus;
        this.type = type;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", currentstep='" + currentstep + '\'' +
                ", payload='" + payload + '\'' +
                ", sagastatus='" + sagastatus + '\'' +
                ", stepstatus='" + stepstatus + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}
